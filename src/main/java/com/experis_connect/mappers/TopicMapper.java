package com.experis_connect.mappers;

import com.experis_connect.models.Topic;
import com.experis_connect.models.dto.topic.TopicDTO;
import com.experis_connect.models.dto.topic.TopicPostDTO;
import com.experis_connect.models.dto.topic.TopicPutDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDTO topicToTopicDTO(Topic topic);
    Collection<TopicDTO> topicToTopicDTO(Collection<TopicDTO> topicDTOS);
    Topic topicPutDTOToTopic(TopicPutDTO topicPutDTO);
    Topic topicPostDTOToTopic(TopicPostDTO topicPostDTO);
}
