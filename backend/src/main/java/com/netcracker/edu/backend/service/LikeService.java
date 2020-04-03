package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Like;

import java.util.Optional;

public interface LikeService {
    Optional<Like> getLikesById(Long id);

    Iterable<Like> getAllLikes();

    Like saveLikes(Like like);

    void deleteLikes(Long id);
}
