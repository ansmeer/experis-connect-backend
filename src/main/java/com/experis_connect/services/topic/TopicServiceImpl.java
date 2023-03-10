package com.experis_connect.services.topic;

import com.experis_connect.exceptions.TopicNotFoundException;
import com.experis_connect.models.Topic;
import com.experis_connect.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TopicServiceImpl implements TopicService{
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
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
}
