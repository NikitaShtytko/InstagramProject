package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServieImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Optional<Post> getPostsById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savePosts(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePosts(Long id) {
        postRepository.deleteById(id);
    }
}
