package com.experis_connect.services.topic;

import com.experis_connect.models.Topic;
import com.experis_connect.services.CRUDService;

import java.util.Set;

public interface TopicService extends CRUDService<Topic, Integer> {
    Set<Topic> searchResultsWithLimitOffset(String search, int offset, int limit);
    Topic addUserToTopic(String userId, int topicId);
}
