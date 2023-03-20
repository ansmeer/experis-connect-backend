package com.experis.experisconnect.services.post;

import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.services.CRUDService;

import java.util.Set;

public interface PostService extends CRUDService<Post, Integer> {
    Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset);
    Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset);
    Set<Post> findAllPostsToUser(String id, String search, int limit, int offset);
    Set<Post> findAllPostsToUserFromSpecificUser(String id, String senderId, String search, int limit, int offset);
    Set<Post> findPostsUserSubscribedTo(String id, String search, int limit, int offset);
    Set<Post> findPostsFromTopicUserIsSubscribedTo(String id);
    Set<Post> findPostsFromGroupUserIsSubscribedTo(String id);
}
