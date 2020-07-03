package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getCommentById(@PathVariable(name = "id") Long id) {
        Optional<Comment> comments = commentService.getById(id);
        return comments.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Comment>> getCommentByPostId(@PathVariable(name = "id") Long id) {
        Iterable<Comment> comments = commentService.findCommentsByPostId(id);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Comment> getAllComments() {
        return commentService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable(name = "id") Long id) {
        commentService.delete(id);
    }
}
