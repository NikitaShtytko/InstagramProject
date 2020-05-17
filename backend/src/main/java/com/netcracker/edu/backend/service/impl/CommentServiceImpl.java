package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.service.CommentService;
import com.netcracker.edu.backend.service.PostService;
import com.netcracker.edu.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    final
    CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
//        Optional<User> user = userService.getById(comment.getUser().getId());
//        Optional<Post> post = postService.getById(comment.getPost().getId());
//        comment.setUser(user.get());
//        comment.setPost(post.get());
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Iterable<Comment> findCommentsByPostId(Long id) {
       return commentRepository.findCommentsByPostId(id);
    }
}
