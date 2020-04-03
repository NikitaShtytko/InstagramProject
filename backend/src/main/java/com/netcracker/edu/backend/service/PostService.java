package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;

import java.util.Optional;

public interface PostService {
    Optional<Post> getPostsById(Long id);

    Iterable<Post> getAllPosts();

    Post savePosts(Post post);

    void deletePosts(Long id);
}
