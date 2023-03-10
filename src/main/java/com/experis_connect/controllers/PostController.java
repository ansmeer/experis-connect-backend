package com.experis_connect.controllers;

import com.experis_connect.mappers.PostMapper;
import com.experis_connect.models.Post;
import com.experis_connect.models.dto.post.PostPostDTO;
import com.experis_connect.models.dto.post.PostPutDTO;
import com.experis_connect.services.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findById(id)));
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAll()));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody PostPostDTO entity){
        Post post = postMapper.postPostDTOToPost(entity);
        postService.add(post);
        URI uri = URI.create("api/v1/post/" + post.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody PostPutDTO entity, @PathVariable int id){
        if(id != entity.getId() || !postService.exists(id))
            return ResponseEntity.badRequest().build();

        Post post = postMapper.postPutDTOToPost(entity);
        postService.update(post);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
