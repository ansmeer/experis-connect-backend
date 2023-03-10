package com.experis_connect.controllers;

import com.experis_connect.mappers.GroupMapper;
import com.experis_connect.models.Groups;
import com.experis_connect.models.dto.group.GroupPostDTO;
import com.experis_connect.models.dto.group.GroupPutDTO;
import com.experis_connect.services.group.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
        if(id != entity.getId() || !groupService.exists(id))
            return ResponseEntity.badRequest().build();

        Groups group = groupMapper.groupPutDTOToGroup(entity);
        groupService.update(group);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
