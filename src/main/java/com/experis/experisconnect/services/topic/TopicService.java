package com.experis.experisconnect.services.topic;

import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.services.CRUDService;

import java.util.Set;

public interface TopicService extends CRUDService<Topic, Integer> {
    Set<Topic> searchResultsWithLimitOffset(String search, int offset, int limit);
    Topic addUserToTopic(String userId, int topicId);
}
