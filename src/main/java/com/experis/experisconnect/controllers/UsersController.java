package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.UsersMapper;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.users.UsersDTO;
import com.experis.experisconnect.models.dto.users.UsersPutDTO;
import com.experis.experisconnect.services.users.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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
    public ResponseEntity<UsersDTO> findCurrentUser(Principal principal){
        // TODO Make this 303 See Other
        String id = principal.getName();
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsersDTO> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, Principal principal){
        String id = principal.getName();
        String name = getNameFromToken(token);

        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setCreatedAt(LocalDate.now().toString());
        user.setUpdatedAt(LocalDate.now().toString());
        usersService.add(user);
        URI uri = URI.create("api/v1/users/" +1);

        return ResponseEntity.created(uri).body(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsersDTO> update(@RequestBody UsersPutDTO entity, @PathVariable String id){
        if(!usersService.exists(id))
            return ResponseEntity.badRequest().build();

        Users users = usersMapper.usersPutDTOToUsers(entity);
        users.setId(id);
        users.setCreatedAt(usersService.findById(id).getCreatedAt());
        users.setUpdatedAt(LocalDate.now().toString());
        usersService.update(users);
        return ResponseEntity.ok(usersMapper.usersToUsersDTO(usersService.findById(id)));
    }

    private String getNameFromToken(String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String[] payloadData = payload.split(",");
        System.out.println(payloadData[23]);
        String payloadName[] = payloadData[23].split(":");
        return payloadName[1].replace("\"", "");
    }
}
