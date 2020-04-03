package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Likes;

import java.util.Optional;

public interface LikesService{
    Optional<Likes> getLikesById(Long id);

    Iterable<Likes> getAllLikes();

    Likes saveLikes(Likes likes);

    void deleteLikes(Long id);
}
