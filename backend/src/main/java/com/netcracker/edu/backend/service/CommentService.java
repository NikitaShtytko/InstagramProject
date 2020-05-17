package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

public interface CommentService extends DefaultService<Comment>{
    Iterable<Comment> findCommentsByPostId(Long id);
}
