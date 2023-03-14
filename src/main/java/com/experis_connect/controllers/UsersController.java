package com.experis_connect.controllers;

import com.experis_connect.mappers.UsersMapper;
import com.experis_connect.models.Users;
import com.experis_connect.models.dto.users.UsersDTO;
import com.experis_connect.models.dto.users.UsersPutDTO;
import com.experis_connect.services.users.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Base64;

@CrossOrigin(origins = {"http://localhost:5173", "https://experis-connect.vercel.app"}, maxAge = 3600)
    // TODO move origins to environment variables
@PreAuthorize("hasRole('USER')")
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
    public ResponseEntity<UsersDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<UsersDTO> findCurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        // TODO Make this 303 See Other
        String id = getIdFromToken(token);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsersDTO> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        String id = getIdFromToken(token);

        Users user = new Users();
        user.setId(id);
        user.setCreatedAt(LocalDate.now().toString());
        user.setUpdatedAt(LocalDate.now().toString());
        usersService.add(user);
        URI uri = URI.create("api/v1/users/" +1);

        return ResponseEntity.created(uri).body(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsersDTO> update(@RequestBody UsersPutDTO entity, @PathVariable String id){
        if(!id.equals(entity.getId()) || !usersService.exists(id))
            return ResponseEntity.badRequest().build();

        Users users = usersMapper.usersPutDTOToUsers(entity);
        Users oldUser = usersService.findById(id);
        if(users.getName() == null){
            users.setName(oldUser.getName());
        }
        if(users.getPicture() == null){
            users.setPicture(oldUser.getPicture());
        }
        if(users.getStatus() == null){
            users.setStatus(oldUser.getStatus());
        }
        if(users.getBio() == null){
            users.setBio(oldUser.getBio());
        }
        if(users.getFunFact() == null){
            users.setFunFact(oldUser.getFunFact());
        }
        users.setUpdatedAt(LocalDate.now().toString());
        usersService.update(users);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
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
