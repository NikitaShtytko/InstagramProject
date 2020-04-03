package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Posts;
import com.netcracker.edu.backend.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostsController {

    private PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService){this.postsService = postsService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Posts> getPostsById(@PathVariable(name = "id") Long id) {
        Optional<Posts> posts = postsService.getPostsById(id);
        if (posts.isPresent()) {
            return ResponseEntity.ok(posts.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Posts> getAllPosts() {
        System.out.println("GET DETECTED");
        return postsService.getAllPosts();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Posts savePosts(@RequestBody Posts posts) {
        return postsService.savePosts(posts);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePosts(@PathVariable(name = "id") Long id) {
        postsService.deletePosts(id);
    }
}
