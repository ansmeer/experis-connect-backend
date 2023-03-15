package com.experis_connect.controllers;

import com.experis_connect.mappers.PostMapper;
import com.experis_connect.models.Post;
import com.experis_connect.models.dto.post.PostDTO;
import com.experis_connect.models.dto.post.PostPostDTO;
import com.experis_connect.models.dto.post.PostPutDTO;
import com.experis_connect.services.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;

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

    @GetMapping
    public ResponseEntity<Collection<PostDTO>> findAll() {
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody PostPostDTO entity) {
        Post post = postMapper.postPostDTOToPost(entity);
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
}
