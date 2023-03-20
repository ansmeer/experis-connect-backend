package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Groups;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.dto.group.GroupDTO;
import com.experis.experisconnect.models.dto.group.GroupPostDTO;
import com.experis.experisconnect.models.dto.group.GroupPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    // @Mapping(target = "posts", qualifiedByName = "postsToPostId")
    GroupDTO groupToGroupDTO(Groups group);
    Collection<GroupDTO> groupToGroupDTO(Collection<Groups> groups);
    Groups groupPutDTOToGroup(GroupPutDTO groupPutDTO);
    Groups groupPostDTOToGroup(GroupPostDTO groupPostDTO);

    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
