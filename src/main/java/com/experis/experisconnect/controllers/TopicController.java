package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.TopicMapper;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.topic.TopicDTO;
import com.experis.experisconnect.models.dto.topic.TopicPostDTO;
import com.experis.experisconnect.models.dto.topic.TopicPutDTO;
import com.experis.experisconnect.services.topic.TopicService;
import com.experis.experisconnect.services.users.UsersService;
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
    public ResponseEntity<TopicDTO> findById(@PathVariable int id){
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Collection<TopicDTO>> findAll(@RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(
                topicService.searchResultsWithLimitOffset(search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody TopicPostDTO entity, Principal principal){
        Topic topic = topicMapper.topicPostDTOToTopic(entity);
        String id = principal.getName();
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
    public ResponseEntity<Object> update(@RequestBody TopicPutDTO entity, @PathVariable int id){
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
    public ResponseEntity<Object> deleteById(@PathVariable int id){
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/join")
    public ResponseEntity<Object> addUserToTopic(Principal principal, @PathVariable int id){
        if(!topicService.exists(id))
            return ResponseEntity.badRequest().build();
        String userId = principal.getName();
        topicService.addUserToTopic(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Collection<TopicDTO>> findTopicsForAUser(Principal principal){
        String userId = principal.getName();
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findTopicsWithUser(userId)));
    }
}
