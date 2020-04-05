package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService = postService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPostsById(@PathVariable(name = "id") Long id) {
        Optional<Post> posts = postService.getById(id);
        return posts.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Post> getAllPosts() {
        System.out.println("GET DETECTED");
        return postService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Post savePosts(@RequestBody Post post) {
        System.out.println("POST DETECTED");
        return postService.save(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePosts(@PathVariable(name = "id") Long id) {
        postService.delete(id);
    }
}
