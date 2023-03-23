package com.experis.experisconnect.controllers;

import com.experis.experisconnect.mappers.PostMapper;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.dto.post.PostDTO;
import com.experis.experisconnect.models.dto.post.PostPostDTO;
import com.experis.experisconnect.models.dto.post.PostPutDTO;
import com.experis.experisconnect.services.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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
    @Operation(summary = "Get a post by its id", tags = {"Posts", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    public ResponseEntity<PostDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Add a post", tags = {"Posts", "Post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content)
    })
    public ResponseEntity<Object> add(@RequestBody PostPostDTO entity) {
        Post post = postMapper.postPostDTOToPost(entity);
        postService.add(post);
        URI uri = URI.create("api/v1/post/" + post.getId());
        return ResponseEntity.created(uri).body(post.getId());
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a post", tags = {"Posts", "Put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, URI does not match request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    public ResponseEntity<Object> update(@RequestBody PostPutDTO entity, @PathVariable int id) {
        if (!postService.exists(id))
            return ResponseEntity.badRequest().build();

        Post post = postMapper.postPutDTOToPost(entity);
        post.setId(id);
        Post oldPost = postService.findById(id);
        post.setCreatedAt(oldPost.getCreatedAt());
        post.setPostTarget(oldPost.getPostTarget());
        post.setSenderId(oldPost.getSenderId());
        post.setReplyParentId(oldPost.getReplyParentId());
        post.setReplies(oldPost.getReplies());
        post.setTargetUser(oldPost.getTargetUser());
        post.setTargetTopic(oldPost.getTargetTopic());
        post.setTargetGroup(oldPost.getTargetGroup());

        postService.update(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a post by its id", tags = {"Posts", "Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{id}")
    @Operation(summary = "Get all posts in a topic", tags = {"Posts", "Topic", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findAllPostsInATopic(@PathVariable int id, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsInTopic(id, searching, limiting, offsetting)));
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "Get all posts in a group", tags = {"Posts", "Group", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findAllPostsInAGroup(@PathVariable int id, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsInGroup(id, searching, limiting, offsetting)));
    }

    @GetMapping("/user")
    @Operation(summary = "Get all posts from a user", tags = {"Posts", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findAllPostsToAUser(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsToUser(userId, searching, limiting, offsetting)));
    }

    @GetMapping("/user/{senderId}")
    @Operation(summary = "Get all posts from a user by its id", tags = {"Posts", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findAllPostsToAUser(Principal principal, @PathVariable String senderId, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findAllPostsToUserFromSpecificUser(userId, senderId, searching, limiting, offsetting)));
    }

    @GetMapping
    @Operation(summary = "Get all posts a user is subscribed to", tags = {"Posts", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findPostsUserIsSubscribedTo(Principal principal, @RequestParam Optional<String> search, Optional<Integer> limit, Optional<Integer> offset) {
        String userId = principal.getName();
        String searching = search.orElse("").toLowerCase();
        int limiting = limit.orElse(999999999);
        int offsetting = offset.orElse(0);
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsUserSubscribedTo(userId, searching, limiting, offsetting)));
    }

    @GetMapping("/topic")
    @Operation(summary = "Get all posts in a topic a user is subscribed to", tags = {"Posts", "Topic", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findPostInTopicUserIsSubscribedTo(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsFromTopicUserIsSubscribedTo(userId)));
    }

    @GetMapping("/group")
    @Operation(summary = "Get all posts in a group a user is subscribed to", tags = {"Posts", "Group", "Users", "Get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    })
    public ResponseEntity<Collection<PostDTO>> findPostInGroupUserIsSubscribedTo(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(postMapper.postToPostDTO(postService.findPostsFromGroupUserIsSubscribedTo(userId)));
    }

    @GetMapping("{id}/replies")
    public ResponseEntity<Collection<PostDTO>> findRepliesToAPost(@PathVariable int id){
        Post post = postService.findById(id);
        return ResponseEntity.ok(postMapper.postToPostDTO(post.getReplies()));
    }
}