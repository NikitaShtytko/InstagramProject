package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Comments;
import com.netcracker.edu.backend.repository.CommentsRepository;
import com.netcracker.edu.backend.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;


    @Override
    public Optional<Comments> getCommentsById(Long id) {
        return commentsRepository.findById(id);
    }

    @Override
    public Comments saveComments(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public void deleteComments(Long id) {
        commentsRepository.deleteById(id);
    }

    @Override
    public Iterable<Comments> getAllComments() {
        return commentsRepository.findAll();
    }
}
