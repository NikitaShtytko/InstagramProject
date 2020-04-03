package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Posts;

import java.util.Optional;

public interface PostsService {
    Optional<Posts> getPostsById(Long id);

    Iterable<Posts> getAllPosts();

    Posts savePosts(Posts posts);

    void deletePosts(Long id);
}
