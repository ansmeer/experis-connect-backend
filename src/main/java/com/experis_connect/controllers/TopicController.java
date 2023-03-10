package com.experis_connect.controllers;

import com.experis_connect.mappers.TopicMapper;
import com.experis_connect.models.Topic;
import com.experis_connect.models.dto.topic.TopicPostDTO;
import com.experis_connect.models.dto.topic.TopicPutDTO;
import com.experis_connect.services.topic.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/topic")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;

    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id){
        return ResponseEntity.ok(topicService.findById(id));
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(topicService.findAll());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody TopicPostDTO entity){
        Topic topic = topicMapper.topicPostDTOToTopic(entity);
        topicService.add(topic);
        URI uri = URI.create("api/v1/topic/" + topic.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody TopicPutDTO entity, @PathVariable int id){
        if(id != entity.getId() || !topicService.exists(id))
            return ResponseEntity.badRequest().build();

        Topic topic = topicMapper.topicPutDTOToTopic(entity);
        topicService.update(topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
