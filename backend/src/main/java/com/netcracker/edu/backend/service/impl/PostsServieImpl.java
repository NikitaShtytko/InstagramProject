package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Posts;
import com.netcracker.edu.backend.repository.PostsRepository;
import com.netcracker.edu.backend.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostsServieImpl implements PostsService {

    @Autowired
    PostsRepository postsRepository;

    @Override
    public Optional<Posts> getPostsById(Long id) {
        return postsRepository.findById(id);
    }

    @Override
    public Iterable<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Posts savePosts(Posts posts) {
        return postsRepository.save(posts);
    }

    @Override
    public void deletePosts(Long id) {
        postsRepository.deleteById(id);
    }
}
