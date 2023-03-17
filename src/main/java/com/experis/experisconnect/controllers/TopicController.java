package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.TopicMapper;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.topic.TopicPostDTO;
import com.experis.experisconnect.models.dto.topic.TopicPutDTO;
import com.experis.experisconnect.services.topic.TopicService;
import com.experis.experisconnect.services.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    @Operation(summary = "Get a topic by its id", tags = {"Topic", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(topicService.findById(id)));
    }

    @GetMapping
    @Operation(summary = "Get all topics", tags = {"Topic", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity findAll(@RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset){
        return ResponseEntity.ok(topicMapper.topicToTopicDTO(
                topicService.searchResultsWithLimitOffset(search.orElse("").toLowerCase(), offset.orElse(0), limit.orElse(99999999))));
    }

    @PostMapping
    @Operation(summary = "Add a topic", tags = {"Topic", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
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
    @Operation(summary = "Update a topic", tags = {"Topic", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Topic updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
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
    @Operation(summary = "Delete a topic by its id", tags = {"Topic", "Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Topic deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
    public ResponseEntity deleteById(@PathVariable int id){
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/join")
    @Operation(summary = "Add a user to a topic", tags = {"Topic", "Users", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content)
    })
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
