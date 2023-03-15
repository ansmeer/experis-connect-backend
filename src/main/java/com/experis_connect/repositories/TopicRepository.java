package com.experis_connect.repositories;

import com.experis_connect.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "select * from Topic t where (lower(t.name) like (%?1%) or (lower(t.description) like (%?1%))) limit ?2 offset ?3", nativeQuery = true)
    Set<Topic> findTopicsByNameWithLimitOffset(String name, int limit, int offset);
}
