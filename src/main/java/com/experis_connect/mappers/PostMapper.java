package com.experis_connect.mappers;

import com.experis_connect.models.Groups;
import com.experis_connect.models.Post;
import com.experis_connect.models.Topic;
import com.experis_connect.models.Users;
import com.experis_connect.models.dto.post.PostDTO;
import com.experis_connect.models.dto.post.PostPostDTO;
import com.experis_connect.models.dto.post.PostPutDTO;
import com.experis_connect.services.group.GroupService;
import com.experis_connect.services.post.PostService;
import com.experis_connect.services.topic.TopicService;
import com.experis_connect.services.users.UsersService;
import org.mapstruct.*;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring", uses = { UsersService.class, GroupService.class, TopicService.class, PostService.class } )
public interface PostMapper {

    @Mapping(target = "target_group", source = "target_group.id")
    @Mapping(target = "target_topic", source = "target_topic.id")
    @Mapping(target = "target_user", source = "target_user.id")
    @Mapping(target = "sender_id", source = "sender_id.id")
    @Mapping(target = "reply_parent_id", source = "reply_parent_id.id")
    PostDTO postToPostDTO(Post post);
    Collection<PostDTO> postToPostDTO(Collection<Post> posts);
    Post postPutDTOToPost(PostPutDTO postPutDTO);
    @Mapping(target = "sender_id",source = "sender_id")
    @Mapping(target = "reply_parent_id",source = "reply_parent_id")
    @Mapping(target = "target_user",source = "target_user")
    @Mapping(target = "target_group",source = "target_group")
    @Mapping(target = "target_topic",source = "target_topic")
    Post postPostDTOToPost(PostPostDTO postPostDTO);
}
