package com.experis_connect.mappers;

import com.experis_connect.models.Post;
import com.experis_connect.models.dto.post.PostDTO;
import com.experis_connect.models.dto.post.PostPostDTO;
import com.experis_connect.models.dto.post.PostPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "target_group", source = "target_group.id")
    @Mapping(target = "target_topic", source = "target_topic.id")
    @Mapping(target = "target_user", source = "target_user.id")
    @Mapping(target = "sender_id", source = "sender_id.id")
    @Mapping(target = "reply_parent_id", source = "reply_parent_id.id")
    PostDTO postToPostDTO(Post post);
    Collection<PostDTO> postToPostDTO(Collection<Post> posts);
    Post postPutDTOToPost(PostPutDTO postPutDTO);
    Post postPostDTOToPost(PostPostDTO postPostDTO);
}
