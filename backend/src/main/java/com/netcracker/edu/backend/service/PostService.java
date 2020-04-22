package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;

public interface PostService extends DefaultService<Post>{
    Iterable<Post> findByUserId(Long id);
}
