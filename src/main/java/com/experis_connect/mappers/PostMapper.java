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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { UsersService.class, GroupService.class, TopicService.class, PostService.class } )
public abstract class PostMapper {

    @Autowired
    protected UsersService usersService;
    @Autowired
    protected GroupService groupService;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected PostService postService;

    @Mapping(target = "target_group", source = "target_group.id")
    @Mapping(target = "target_topic", source = "target_topic.id")
    @Mapping(target = "target_user", source = "target_user.id")
    @Mapping(target = "sender_id", source = "sender_id.id")
    @Mapping(target = "reply_parent_id", source = "reply_parent_id.id")
    public abstract PostDTO postToPostDTO(Post post);
    public abstract Collection<PostDTO> postToPostDTO(Collection<Post> posts);
    public abstract Post postPutDTOToPost(PostPutDTO postPutDTO);
    @Mapping(target = "sender_id",source = "sender_id")
    @Mapping(target = "reply_parent_id",source = "reply_parent_id", qualifiedByName = "parentPostIdToPost")
    @Mapping(target = "target_user",source = "target_user", qualifiedByName = "userIdToUser")
    @Mapping(target = "target_group",source = "target_group", qualifiedByName = "groupIdToGroup")
    @Mapping(target = "target_topic",source = "target_topic", qualifiedByName = "topicIdToTopic")
    public abstract Post postPostDTOToPost(PostPostDTO postPostDTO);

    @Named(value = "userIdToUser")
    Users map(Integer value){
        try{
            return usersService.findById(value);
        }catch(Exception e){
            return null;
        }
    }
    @Named(value = "groupIdToGroup")
    Groups maps(Integer value){
        try{
            return groupService.findById(value);
        }catch(Exception e){
            return null;
        }
    }
    @Named(value = "topicIdToTopic")
    Topic mapen(Integer value){
        try{
            return topicService.findById(value);
        }catch(Exception e){
            return null;
        }
    }
    @Named(value = "parentPostIdToPost")
    Post maper(Integer value){
        try{
            return postService.findById(value);
        }catch(Exception e){
            return null;
        }
    }
}
