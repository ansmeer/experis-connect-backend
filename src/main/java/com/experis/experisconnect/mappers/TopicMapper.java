package com.experis.experisconnect.mappers;

import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.models.dto.topic.TopicDTO;
import com.experis.experisconnect.models.dto.topic.TopicPostDTO;
import com.experis.experisconnect.models.dto.topic.TopicPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    //@Mapping(target = "posts", qualifiedByName = "postsToPostId")
    TopicDTO topicToTopicDTO(Topic topic);
    Collection<TopicDTO> topicToTopicDTO(Collection<Topic> topics);
    Topic topicPutDTOToTopic(TopicPutDTO topicPutDTO);
    Topic topicPostDTOToTopic(TopicPostDTO topicPostDTO);
    @Named(value = "postsToPostId")
    default Set<Integer> map(Set<Post> value){
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
