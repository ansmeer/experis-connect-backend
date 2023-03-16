package com.experis.experisconnect.services.post;

import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.services.CRUDService;

import java.util.Set;

public interface PostService extends CRUDService<Post, Integer> {
    Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset);
    Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset);
}
