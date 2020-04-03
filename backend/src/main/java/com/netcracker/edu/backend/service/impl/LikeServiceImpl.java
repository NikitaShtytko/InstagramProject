package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.repository.LikeRepository;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Override
    public Optional<Like> getLikesById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public Iterable<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public Like saveLikes(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public void deleteLikes(Long id) {
        likeRepository.deleteById(id);
    }
}
