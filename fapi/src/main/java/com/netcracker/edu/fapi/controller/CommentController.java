package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping
    public ResponseEntity<Iterable<Comment>> getAllComments(){
        return ResponseEntity.ok(commentService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable String id) throws InterruptedException {
        Long commentId = Long.valueOf(id);
        return ResponseEntity.ok(commentService.getById(commentId));
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Comment>> getCommentByPostId(@PathVariable(name = "id") Long id) {
        Iterable<Comment> comments = commentService.findCommentsByPostId(id);
        return ResponseEntity.ok(comments);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        if (comment != null) {
            return ResponseEntity.ok(commentService.save(comment));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable String id) {
        commentService.delete(Long.valueOf(id));
    }
}
