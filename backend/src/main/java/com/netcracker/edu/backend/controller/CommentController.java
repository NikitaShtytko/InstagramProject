package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getCommentsById(@PathVariable(name = "id") Long id) {
        Optional<Comment> comments = commentService.getCommentsById(id);
        return comments.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Comment> getAllComments() {
        System.out.println("GET DETECTED");
        return commentService.getAllComments();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Comment saveComments(@RequestBody Comment comment) {
        return commentService.saveComments(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComments(@PathVariable(name = "id") Long id) {
        commentService.deleteComments(id);
    }
}
