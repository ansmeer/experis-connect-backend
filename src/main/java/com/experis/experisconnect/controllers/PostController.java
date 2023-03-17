package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.PostMapper;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.dto.post.PostDTO;
import com.experis.experisconnect.models.dto.post.PostPostDTO;
import com.experis.experisconnect.models.dto.post.PostPutDTO;
import com.experis.experisconnect.services.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173", "https://experis-connect.vercel.app"}, maxAge = 3600)
// TODO move origins to environment variables
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody PostPostDTO entity) {
        Post post = postMapper.postPostDTOToPost(entity);
        post.setCreatedAt(LocalDate.now().toString());
        post.setUpdatedAt(LocalDate.now().toString());
        postService.add(post);
        URI uri = URI.create("api/v1/post/" + post.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody PostPutDTO entity, @PathVariable int id) {
        if (!postService.exists(id))
            return ResponseEntity.badRequest().build();

        Post post = postMapper.postPutDTOToPost(entity);
        post.setId(id);
        post.setCreatedAt(postService.findById(id).getCreatedAt());
        post.setUpdatedAt(LocalDate.now().toString());
        postService.update(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Collection<PostDTO>> findAllPostsInATopic(@PathVariable int id, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsInTopic(id, searching, limiting, offsetting)));
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<Collection<PostDTO>> findAllPostsInAGroup(@PathVariable int id, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsInGroup(id, searching, limiting, offsetting)));
    }

    @GetMapping("/user")
    public ResponseEntity<Collection<PostDTO>> findAllPostsToAUser(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsToUser(userId, searching, limiting, offsetting)));
    }

    @GetMapping("/user/{senderId}")
    public ResponseEntity<Collection<PostDTO>> findAllPostsToAUser(Principal principal, @PathVariable String senderId, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsToUserFromSpecificUser(userId, senderId, searching, limiting, offsetting)));
    }

    @GetMapping
    public ResponseEntity<Collection<PostDTO>> findPostsUserIsSubscribedTo(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsUserSubscribedTo(userId, searching, limiting, offsetting)));
    }

    @GetMapping("/topic")
    public ResponseEntity<Collection<PostDTO>> findPostInTopicUserIsSubscribedTo(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsFromTopicUserIsSubscribedTo(userId)));
    }

    @GetMapping("/group")
    public ResponseEntity<Collection<PostDTO>> findPostInGroupUserIsSubscribedTo(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsFromGroupUserIsSubscribedTo(userId)));
    }
}