package com.experis_connect.mappers;

import com.experis_connect.models.Groups;
import com.experis_connect.models.dto.group.GroupDTO;
import com.experis_connect.models.dto.group.GroupPostDTO;
import com.experis_connect.models.dto.group.GroupPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDTO groupToGroupDTO(Groups group);
    Collection<Groups> groupToGroupDTO(Collection<Groups> groups);
    Groups groupPutDTOToGroup(GroupPutDTO groupPutDTO);
    Groups groupPostDTOToGroup(GroupPostDTO groupPostDTO);
}
