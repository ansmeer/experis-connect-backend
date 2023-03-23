package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.group.GroupMiniDTO;
import com.experis.experisconnect.models.dto.post.PostDTO;
import com.experis.experisconnect.models.dto.post.PostPostDTO;
import com.experis.experisconnect.models.dto.post.PostPutDTO;
import com.experis.experisconnect.models.dto.topic.TopicMiniDTO;
import com.experis.experisconnect.models.dto.users.SenderDTO;
import com.experis.experisconnect.services.group.GroupService;
import com.experis.experisconnect.services.post.PostService;
import com.experis.experisconnect.services.topic.TopicService;
import com.experis.experisconnect.services.users.UsersService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UsersService.class, GroupService.class, TopicService.class, PostService.class})
public abstract class PostMapper {

    @Autowired
    protected UsersService usersService;
    @Autowired
    protected GroupService groupService;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected PostService postService;

    @Mapping(target = "targetGroup", source = "targetGroup", qualifiedByName = "groupToMiniDTO")
    @Mapping(target = "targetTopic", source = "targetTopic", qualifiedByName = "topicToMiniDTO")
    @Mapping(target = "targetUser", source = "targetUser.id")
    @Mapping(target = "senderId", source = "senderId", qualifiedByName = "userToSenderDTO")
    @Mapping(target = "replyParentId", source = "replyParentId.id")
    @Mapping(target = "replies", source = "replies", qualifiedByName = "postsToPostId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    public abstract PostDTO postToPostDTO(Post post);

    public abstract Collection<PostDTO> postToPostDTO(Collection<Post> posts);

    public abstract Post postPutDTOToPost(PostPutDTO postPutDTO);

    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "replyParentId", source = "replyParentId", qualifiedByName = "parentPostIdToPost")
    @Mapping(target = "targetUser", source = "targetUser", qualifiedByName = "userIdToUser")
    @Mapping(target = "targetGroup", source = "targetGroup", qualifiedByName = "groupIdToGroup")
    @Mapping(target = "targetTopic", source = "targetTopic", qualifiedByName = "topicIdToTopic")
    public abstract Post postPostDTOToPost(PostPostDTO postPostDTO);

    @Named(value = "userToSenderDTO")
    SenderDTO mapSend(Users value){
        if(value == null)
            return null;
        SenderDTO sender = new SenderDTO();
        sender.setId(value.getId());
        sender.setName(value.getName());
        sender.setPicture(value.getPicture());
        return sender;
    }

    @Named(value = "groupToMiniDTO")
    GroupMiniDTO mapMiniGroup(Groups group){
        if(group == null)
            return null;
        GroupMiniDTO miniGroup = new GroupMiniDTO();
        miniGroup.setId(group.getId());
        miniGroup.setName((group.getName()));
        return miniGroup;
    }

    @Named(value = "topicToMiniDTO")
    TopicMiniDTO mapMiniTopic(Topic topic){
        if (topic == null)
            return null;
        TopicMiniDTO miniTopic = new TopicMiniDTO();
        miniTopic.setId(topic.getId());
        miniTopic.setName(topic.getName());
        return miniTopic;
    }

    @Named(value = "userIdToUser")
    Users map(String value) {
        try {
            return usersService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "groupIdToGroup")
    Groups maps(Integer value) {
        try {
            return groupService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "topicIdToTopic")
    Topic mapen(Integer value) {
        try {
            return topicService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "parentPostIdToPost")
    Post maper(Integer value) {
        try {
            return postService.findById(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named(value = "postsToPostId")
    Set<Integer> map(Set<Post> value) {
        if(value == null)
            return new HashSet<>();
        return value.stream()
                .map(Post::getId)
                .collect(Collectors.toSet());
    }

    java.time.ZonedDateTime timeMap(Instant instant){
        return instant == null ? null : ZonedDateTime.from(instant);
    }
}
