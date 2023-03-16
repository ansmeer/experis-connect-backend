package com.experis.experisconnect.services.post;

import com.experis.experisconnect.exceptions.PostNotFoundException;
import com.experis.experisconnect.models.Post;
import com.experis.experisconnect.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Collection<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post add(Post entity) {
        return postRepository.save(entity);
    }

    @Override
    public Post update(Post entity) {
        return postRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return postRepository.existsById(id);
    }

    @Override
    public Set<Post> findAllPostsInTopic(int id, String search, int limit, int offset) {
        return postRepository.findPostsInATopicWithSearchLimitOffset(id, search, limit, offset);
    }

    @Override
    public Set<Post> findAllPostsInGroup(int id, String search, int limit, int offset) {
        return postRepository.findPostsInAGroupWithSearchLimitOffset(id, search, limit, offset);
    }
}
