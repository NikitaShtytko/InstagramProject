package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;


    @Override
    public Optional<Comment> getCommentsById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveComments(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComments(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
