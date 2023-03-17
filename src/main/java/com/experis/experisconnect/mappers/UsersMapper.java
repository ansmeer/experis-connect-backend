package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.Users;
import com.experis.experisconnect.models.dto.users.UsersDTO;
import com.experis.experisconnect.models.dto.users.UsersPostDTO;
import com.experis.experisconnect.models.dto.users.UsersPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    // @Mapping(target = "posts", qualifiedByName = "postsToPostId")
    // @Mapping(target = "posted", qualifiedByName = "postsToPostId")
    @Mapping(target = "groups", qualifiedByName = "groupsToGroupsId")
    @Mapping(target = "topics", qualifiedByName = "topicsToTopicsId")
    UsersDTO usersToUsersDTO(Users users);
    Collection<UsersDTO> usersToUsersDTO(Collection<Users> users);
    Users usersPutDTOToUsers(UsersPutDTO usersPutDTO);
    Users usersPostDTOToUsers(UsersPostDTO usersPostDTO);

    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
    @Named(value = "groupsToGroupsId")
    default Set<Integer> mapGroups(Set<Groups> value){
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
    @Named(value = "topicsToTopicsId")
    default Set<Integer> mapTopics(Set<Topic> value){
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
