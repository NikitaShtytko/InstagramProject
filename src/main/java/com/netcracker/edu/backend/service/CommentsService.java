package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comments;

import java.util.Optional;

public interface CommentsService {
    Optional<Comments> getCommentsById(Long id);

    Comments saveComments(Comments comments);

    void deleteComments(Long id);

    Iterable<Comments> getAllComments();
}
