package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.GroupMapper;
import com.experis.experisconnect.mappers.UsersMapper;
import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.group.GroupDTO;
import com.experis.experisconnect.models.dto.group.GroupPostDTO;
import com.experis.experisconnect.models.dto.group.GroupPutDTO;
import com.experis.experisconnect.models.dto.users.UsersDTO;
import com.experis.experisconnect.services.group.GroupService;
import com.experis.experisconnect.services.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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
    private final UsersMapper usersMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper, UsersService usersService, UsersMapper usersMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a group by its id", tags = {"Group", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GroupDTO.class))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<GroupDTO> findById(Principal principal, @PathVariable int id){
        if(!groupService.exists(id))
            return ResponseEntity.notFound().build();
        String userId = principal.getName();
        GroupDTO group = groupMapper.groupToGroupDTO(groupService.findByIdWhereUserHasAccess(userId, id));
        if(group == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @Operation(summary = "Get all groups", tags = {"Group", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GroupDTO.class)))})
    })
    public ResponseEntity<Collection<GroupDTO>> findAll(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        String userId = principal.getName();
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(
                groupService.searchResultsWithLimitOffset(userId, search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    @Operation(summary = "Add a group", tags = {"Group", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<Object> add(@RequestBody GroupPostDTO entity, Principal principal){
        Groups group = groupMapper.groupPostDTOToGroup(entity);
        String id = principal.getName();
        Set<Users> user = new HashSet<>();
        user.add(usersService.findById(id));
        group.setUsers(user);
        groupService.add(group);
        URI uri = URI.create("api/v1/group/" + group.getId());
        return ResponseEntity.created(uri).body(group.getId());
    }
    @PutMapping("{id}")
    @Operation(summary = "Update a group", tags = {"Group", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Object> update(Principal principal, @RequestBody GroupPutDTO entity, @PathVariable int id){
        if(!groupService.exists(id))
            return ResponseEntity.badRequest().build();
        if(!groupService.checkIfUserInGroup(principal.getName(), id))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Groups group = groupMapper.groupPutDTOToGroup(entity);
        Groups oldGroup = groupService.findById(id);
        group.setId(id);
        group.setCreatedAt(oldGroup.getCreatedAt());
        group.setUsers(oldGroup.getUsers());
        groupService.update(group);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("{id}/join")
    @Operation(summary = "Add a user to a group", tags = {"Group", "Users", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "401", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Object> addUserToGroup(Principal principal, @PathVariable int id, @RequestParam Optional<String> user){
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        boolean privateGroup = groupService.findById(id).isPrivate();
        if(privateGroup) {
            if (!groupService.checkIfUserInGroup(principal.getName(), id))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String userId= user.orElse("");
        if(userId.equals("")) {
            userId = principal.getName();
        }
        groupService.addUserToGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/leave")
    @Operation(summary = "Remove a user from a group", tags = {"Group", "Users", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Object> removeUserFromGroup(Principal principal, @PathVariable int id){
        if (!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        String userId = principal.getName();
        groupService.removeUserFromGroup(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    @Operation(summary = "Get all groups for a user", tags = {"Group", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GroupDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Collection<GroupDTO>> findGroupsForAUser(Principal principal){
        String userId = principal.getName();
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findGroupsWithUser(userId)));
    }

    @GetMapping("{id}/user/list")
    @Operation(summary = "Get all users in a group", tags = {"Group", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UsersDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Group not found", content = @Content)
    })
    public ResponseEntity<Collection<UsersDTO>> findAllMembers(@PathVariable int id){
        Groups groups = groupService.findById(id);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(groups.getUsers()));
    }
}
