package com.experis.experisconnect.repositories;

import com.experis.experisconnect.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select * from Post p where p.topic_id = ?1 and ((lower(p.title) like (%?2%) or (lower(p.content) like (%?2%)))) limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsInATopicWithSearchLimitOffset(int topicId, String search, int limit, int offset);

    @Query(value = "select * from Post p where p.group_id = ?1 and ((lower(p.title) like (%?2%) or (lower(p.content) like (%?2%)))) limit ?3 offset ?4", nativeQuery = true)
    Set<Post> findPostsInAGroupWithSearchLimitOffset(int topicId, String search, int limit, int offset);
}
