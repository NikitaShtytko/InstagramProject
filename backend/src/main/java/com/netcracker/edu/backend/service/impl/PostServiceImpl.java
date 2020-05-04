package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    final
    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
//        sessionFactory.getCurrentSession().update(post.getLikes());
        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id); }

    @Override
    public Iterable<Post> findByUserId(Long id) {
        return postRepository.findByUserId(id);
    }
}


