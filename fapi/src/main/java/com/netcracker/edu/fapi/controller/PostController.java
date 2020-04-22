package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping
    public ResponseEntity<Iterable<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) throws InterruptedException {
        Long postId = Long.valueOf(id);
        return ResponseEntity.ok(postService.getById(postId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        if (post != null) {
            return ResponseEntity.ok(postService.save(post));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id) {
        postService.delete(Long.valueOf(id));
    }

    @RequestMapping(value = "/user/{id}")
    public  ResponseEntity<Iterable<Post>>getPostsByUserId(@PathVariable String id) throws InterruptedException {
        Long postId = Long.valueOf(id);
        return ResponseEntity.ok(postService.findByUserId(postId));
    }
}
