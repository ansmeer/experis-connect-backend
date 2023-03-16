package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.post.PostDTO;
import com.experis.experisconnect.models.dto.post.PostPostDTO;
import com.experis.experisconnect.models.dto.post.PostPutDTO;
import com.experis.experisconnect.services.group.GroupService;
import com.experis.experisconnect.services.post.PostService;
import com.experis.experisconnect.services.topic.TopicService;
import com.experis.experisconnect.services.users.UsersService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
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

    @Mapping(target = "targetGroup", source = "targetGroup.id")
    @Mapping(target = "targetTopic", source = "targetTopic.id")
    @Mapping(target = "targetUser", source = "targetUser.id")
    @Mapping(target = "senderId", source = "senderId.id")
    @Mapping(target = "replyParentId", source = "replyParentId.id")
    @Mapping(target = "replies", source = "replies", qualifiedByName = "postsToPostId")
    public abstract PostDTO postToPostDTO(Post post);

    public abstract Collection<PostDTO> postToPostDTO(Collection<Post> posts);

    public abstract Post postPutDTOToPost(PostPutDTO postPutDTO);

    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "replyParentId", source = "replyParentId", qualifiedByName = "parentPostIdToPost")
    @Mapping(target = "targetUser", source = "targetUser", qualifiedByName = "userIdToUser")
    @Mapping(target = "targetGroup", source = "targetGroup", qualifiedByName = "groupIdToGroup")
    @Mapping(target = "targetTopic", source = "targetTopic", qualifiedByName = "topicIdToTopic")
    public abstract Post postPostDTOToPost(PostPostDTO postPostDTO);

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
        if (value == null)
            return null;
        return value.stream()
                .map(Post::getId)
                .collect(Collectors.toSet());
    }
}
