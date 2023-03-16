package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.GroupMapper;
import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.group.GroupDTO;
import com.experis.experisconnect.models.dto.group.GroupPostDTO;
import com.experis.experisconnect.models.dto.group.GroupPutDTO;
import com.experis.experisconnect.services.group.GroupService;
import com.experis.experisconnect.services.users.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:5173", "https://experis-connect.vercel.app"}, maxAge = 3600)
    // TODO move origins to environment variables
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(path = "api/v1/group")
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final UsersService usersService;

    public GroupController(GroupService groupService, GroupMapper groupMapper, UsersService usersService) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GroupDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Collection<GroupDTO>> findAll(@RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(
                groupService.searchResultsWithLimitOffset(search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody GroupPostDTO entity, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        Groups group = groupMapper.groupPostDTOToGroup(entity);
        String id = getIdFromToken(token);
        Set<Users> user = new HashSet<>();
        user.add(usersService.findById(id));
        group.setUsers(user);
        group.setCreatedAt(LocalDate.now().toString());
        group.setUpdatedAt(LocalDate.now().toString());
        groupService.add(group);
        URI uri = URI.create("api/v1/group/" + group.getId());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody GroupPutDTO entity, @PathVariable int id){
        if(!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        Groups group = groupMapper.groupPutDTOToGroup(entity);
        group.setId(id);
        group.setCreatedAt(groupService.findById(id).getCreatedAt());
        group.setUpdatedAt(LocalDate.now().toString());
        groupService.update(group);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("{id}/join")
    public ResponseEntity<Object> addUserToGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int id, @RequestParam Optional<String> user){
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        String userId= user.orElse("");
        if(userId.equals("")) {
            userId = getIdFromToken(token);
        }
        groupService.addUserToGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    private String getIdFromToken(String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String[] payloadData = payload.split(",");
        payloadData = payloadData[6].split(":");
        String id = payloadData[1].replace("\"", "");

        return id;
    }
}
