package com.experis_connect.mappers;

import com.experis_connect.models.Post;
import com.experis_connect.models.Topic;
import com.experis_connect.models.dto.topic.TopicDTO;
import com.experis_connect.models.dto.topic.TopicPostDTO;
import com.experis_connect.models.dto.topic.TopicPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "posts", qualifiedByName = "postsToPostId")
    TopicDTO topicToTopicDTO(Topic topic);
    Collection<TopicDTO> topicToTopicDTO(Collection<Topic> topics);
    Topic topicPutDTOToTopic(TopicPutDTO topicPutDTO);
    Topic topicPostDTOToTopic(TopicPostDTO topicPostDTO);
    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
