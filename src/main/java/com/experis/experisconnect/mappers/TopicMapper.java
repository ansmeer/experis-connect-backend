package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.dto.topic.TopicDTO;
import com.experis.experisconnect.models.dto.topic.TopicPostDTO;
import com.experis.experisconnect.models.dto.topic.TopicPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    //@Mapping(target = "posts", qualifiedByName = "postsToPostId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    TopicDTO topicToTopicDTO(Topic topic);
    Collection<TopicDTO> topicToTopicDTO(Collection<Topic> topics);
    Topic topicPutDTOToTopic(TopicPutDTO topicPutDTO);
    Topic topicPostDTOToTopic(TopicPostDTO topicPostDTO);
    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        if(value == null)
            return new HashSet<>();
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
    default java.time.ZonedDateTime timeMap(Instant instant){
        return instant == null ? null : ZonedDateTime.from(instant);
    }
}
