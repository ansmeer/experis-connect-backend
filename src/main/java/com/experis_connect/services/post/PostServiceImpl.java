package com.experis_connect.services.post;

import com.experis_connect.exceptions.PostNotFoundException;
import com.experis_connect.models.Post;
import com.experis_connect.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
}
