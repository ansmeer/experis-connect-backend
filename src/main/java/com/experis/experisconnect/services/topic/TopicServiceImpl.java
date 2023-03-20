package com.experis.experisconnect.services.topic;

import com.experis.experisconnect.exceptions.TopicNotFoundException;
import com.experis.experisconnect.models.Topic;
import com.experis.experisconnect.repositories.TopicRepository;
import com.experis.experisconnect.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService{
    private final TopicRepository topicRepository;
    private final UsersRepository usersRepository;

    public TopicServiceImpl(TopicRepository topicRepository, UsersRepository usersRepository) {
        this.topicRepository = topicRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Topic findById(Integer id) {
        return topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
    }

    @Override
    public Collection<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic add(Topic entity) {
        return topicRepository.save(entity);
    }

    @Override
    public Topic update(Topic entity) {
        return topicRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        topicRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return topicRepository.existsById(id);
    }

    @Override
    public Set<Topic> searchResultsWithLimitOffset(String search, int offset, int limit) {
        return topicRepository.findTopicsByNameWithLimitOffset(search, limit, offset);
    }

    @Override
    public Topic addUserToTopic(String userId, int topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));

        topic.getUsers().add(usersRepository.findById(userId).get());

        return topicRepository.save(topic);
        // topicService.findById(id).getUsers().add(usersService.findById(userId));

    }

    @Override
    public Set<Topic> findTopicsWithUser(String userId) {
        return topicRepository.findTopicsAUserIsIn(userId);
    }
}
