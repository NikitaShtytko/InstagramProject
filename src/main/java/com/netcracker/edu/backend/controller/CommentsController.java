package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comments;
import com.netcracker.edu.backend.service.CommentsService;
import com.netcracker.edu.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Service
public class CommentsController {

    private CommentsService commentsService;

    @Autowired
    private CommentsController(UsersService usersService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comments> getCommentsById(@PathVariable(name = "id") Long id) {
        Optional<Comments> comments = commentsService.getCommentsById(id);
        if (comments.isPresent()) {
            return ResponseEntity.ok(comments.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Comments> getAllComments() {
        System.out.printf("GOVNO DETECTED");
        return commentsService.getAllComments();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Comments saveComments(@RequestBody Comments comments) {
        return commentsService.saveComments(comments);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComments(@PathVariable(name = "id") Long id) {
        commentsService.deleteComments(id);
    }
}
