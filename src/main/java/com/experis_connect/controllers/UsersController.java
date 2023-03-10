package com.experis_connect.controllers;

import com.experis_connect.mappers.UsersMapper;
import com.experis_connect.models.Users;
import com.experis_connect.models.dto.users.UsersPostDTO;
import com.experis_connect.models.dto.users.UsersPutDTO;
import com.experis_connect.services.users.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/user")
public class UsersController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    public UsersController(UsersService usersService, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(usersService.findById(id));
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(usersService.findAll());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody UsersPostDTO entity){
        Users users = usersMapper.usersPostDTOToUsers(entity);
        usersService.add(users);
        URI uri = URI.create("api/v1/users/" + users.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UsersPutDTO entity, @PathVariable int id){
        if(id != entity.getId() || !usersService.exists(id))
            return ResponseEntity.badRequest().build();

        Users users = usersMapper.usersPutDTOToUsers(entity);
        usersService.update(users);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
