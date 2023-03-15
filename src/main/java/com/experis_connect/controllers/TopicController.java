package com.experis_connect.controllers;

import com.experis_connect.mappers.TopicMapper;
import com.experis_connect.models.Topic;
import com.experis_connect.models.Users;
import com.experis_connect.models.dto.topic.TopicPostDTO;
import com.experis_connect.models.dto.topic.TopicPutDTO;
import com.experis_connect.services.topic.TopicService;
import com.experis_connect.services.users.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:5173", "https://experis-connect.vercel.app"}, maxAge = 3600)
    // TODO move origins to environment variables
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(path = "api/v1/topic")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final UsersService usersService;

    public TopicController(TopicService topicService, TopicMapper topicMapper, UsersService usersService) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findById(id)));
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        String name = search.orElse("").toLowerCase();
        int size = limit.orElse(10);
        int skips = offset.orElse(0);
        System.out.println("Name: " + name + ", Limit: " + size + ", Offset: " + skips);
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.searchResultsWithLimitOffset(name, skips, size)));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody TopicPostDTO entity, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        Topic topic = topicMapper.topicPostDTOToTopic(entity);
        String id = getIdFromToken(token);
        Set<Users> user = new HashSet<>();
        user.add(usersService.findById(id));
        topic.setUsers(user);
        topic.setCreatedAt(LocalDate.now().toString());
        topic.setUpdatedAt(LocalDate.now().toString());

        topicService.add(topic);
        URI uri = URI.create("api/v1/topic/" + topic.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody TopicPutDTO entity, @PathVariable int id){
        if(!topicService.exists(id))
            return ResponseEntity.badRequest().build();

        Topic topic = topicMapper.topicPutDTOToTopic(entity);
        topic.setId(id);
        topic.setCreatedAt(topicService.findById(id).getCreatedAt());
        topic.setUpdatedAt(LocalDate.now().toString());
        topicService.update(topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/join")
    public ResponseEntity<Object> addUserToTopic(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int id){
        if(!topicService.exists(id))
            return ResponseEntity.badRequest().build();
        String userId = getIdFromToken(token);
        topicService.addUserToTopic(userId, id);
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
