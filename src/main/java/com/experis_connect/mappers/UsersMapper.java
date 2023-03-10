package com.experis_connect.mappers;

import com.experis_connect.models.Users;
import com.experis_connect.models.dto.users.UsersDTO;
import com.experis_connect.models.dto.users.UsersPostDTO;
import com.experis_connect.models.dto.users.UsersPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDTO usersToUsersDTO(Users users);
    Collection<UsersDTO> usersToUsersDTO(Collection<Users> users);
    Users usersPutDTOToUsers(UsersPutDTO usersPutDTO);
    Users usersPostDTOToUsers(UsersPostDTO usersPostDTO);
}
