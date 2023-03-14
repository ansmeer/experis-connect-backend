package com.experis_connect.controllers;

import com.experis_connect.mappers.GroupMapper;
import com.experis_connect.models.Groups;
import com.experis_connect.models.dto.group.GroupPostDTO;
import com.experis_connect.models.dto.group.GroupPutDTO;
import com.experis_connect.services.group.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

@CrossOrigin(origins = {"http://localhost:5173", "https://experis-connect.vercel.app"}, maxAge = 3600)
    // TODO move origins to environment variables
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(path = "api/v1/group")
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findById(id)));
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(groupMapper.groupToGroupDTO(groupService.findAll()));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody GroupPostDTO entity){
        Groups group = groupMapper.groupPostDTOToGroup(entity);
        groupService.add(group);
        URI uri = URI.create("api/v1/group/" + group.getId());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody GroupPutDTO entity, @PathVariable int id){
        if(!groupService.exists(id))
            return ResponseEntity.badRequest().build();

        Groups group = groupMapper.groupPutDTOToGroup(entity);
        group.setId(id);
        group.setCreated_at(groupService.findById(id).getCreated_at());
        group.setUpdated_at(LocalDate.now().toString());
        groupService.update(group);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
