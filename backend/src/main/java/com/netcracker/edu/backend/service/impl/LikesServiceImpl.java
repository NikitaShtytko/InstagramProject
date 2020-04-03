package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Likes;
import com.netcracker.edu.backend.repository.LikesRepository;
import com.netcracker.edu.backend.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService{

    @Autowired
    LikesRepository likesRepository;

    @Override
    public Optional<Likes> getLikesById(Long id) {
        return likesRepository.findById(id);
    }

    @Override
    public Iterable<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    @Override
    public Likes saveLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public void deleteLikes(Long id) {
        likesRepository.deleteById(id);
    }
}
