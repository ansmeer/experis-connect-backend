package com.experis_connect.mappers;

import com.experis_connect.models.Post;
import com.experis_connect.models.Users;
import com.experis_connect.models.dto.users.UsersDTO;
import com.experis_connect.models.dto.users.UsersPostDTO;
import com.experis_connect.models.dto.users.UsersPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "posts", qualifiedByName = "postsToPostId")
    @Mapping(target = "posted", qualifiedByName = "postsToPostId")
    UsersDTO usersToUsersDTO(Users users);
    Collection<UsersDTO> usersToUsersDTO(Collection<Users> users);
    Users usersPutDTOToUsers(UsersPutDTO usersPutDTO);
    Users usersPostDTOToUsers(UsersPostDTO usersPostDTO);

    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
