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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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
    public ResponseEntity<GroupDTO> findById(Principal principal, @PathVariable int id){
        String userId = principal.getName();
        GroupDTO group = groupMapper.groupToGroupDTO(groupService.findByIdWhereUserHasAccess(userId, id));
        if(group == null)
            return new ResponseEntity("This group is private or does not exist", HttpStatus.FORBIDDEN);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<Collection<GroupDTO>> findAll(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        String userId = principal.getName();
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(
                groupService.searchResultsWithLimitOffset(userId, search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody GroupPostDTO entity, Principal principal){
        Groups group = groupMapper.groupPostDTOToGroup(entity);
        String id = principal.getName();
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
    public ResponseEntity<Object> addUserToGroup(Principal principal, @PathVariable int id, @RequestParam Optional<String> user){
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        boolean privateGroup = groupService.findById(id).isPrivate();
        if(privateGroup) {
            if (!groupService.checkIfUserInGroup(principal.getName(), id))
                return new ResponseEntity<>("This is a private group. To join, request access from a member", HttpStatus.FORBIDDEN);
        }
        String userId= user.orElse("");
        if(userId.equals("")) {
            userId = principal.getName();
        }
        groupService.addUserToGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Collection<GroupDTO>> findGroupsForAUser(Principal principal){
        String userId = principal.getName();
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findGroupsWithUser(userId)));
    }
}
