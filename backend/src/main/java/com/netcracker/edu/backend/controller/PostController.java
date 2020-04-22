package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService = postService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") Long id) {
        Optional<Post> post = postService.getById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Post> getAllPosts() {
        return postService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Post savePost(@RequestBody Post post) {
        return postService.save(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable(name = "id") Long id) {
        postService.delete(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Iterable<Post> getPostByUser(@PathVariable(name = "id") Long id) {
        Iterable<Post> post = postService.findByUserId(id);
        return post;
    }
}