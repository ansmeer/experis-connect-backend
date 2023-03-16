package com.experis_connect.services.post;

import com.experis_connect.models.Post;
import com.experis_connect.services.CRUDService;

import java.util.Set;

public interface PostService extends CRUDService<Post, Integer> {
    Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset);
    Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset);
}
