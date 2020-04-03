package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> getCommentsById(Long id);

    Comment saveComments(Comment comment);

    void deleteComments(Long id);

    Iterable<Comment> getAllComments();
}
