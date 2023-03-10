package com.experis_connect.mappers;

import com.experis_connect.models.Post;
import com.experis_connect.models.dto.post.PostDTO;
import com.experis_connect.models.dto.post.PostPostDTO;
import com.experis_connect.models.dto.post.PostPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO postToPostDTO(Post post);
    Collection<PostDTO> postToPostDTO(Collection<Post> posts);
    Post postPutDTOToPost(PostPutDTO postPutDTO);
    Post postPostDTOToPost(PostPostDTO postPostDTO);
}
