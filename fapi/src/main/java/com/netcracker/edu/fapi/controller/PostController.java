package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import com.netcracker.edu.fapi.util.Converter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
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
        Post post = postService.getById(postId);
        //post.setPhoto(Converter.convertBase64ToByteArray(post.getPhoto()));
        return ResponseEntity.ok(post);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> savePost(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                         @RequestParam("post") String post) throws IOException {
        post = post.replace("\"photo\":{},", "");
        Post postMain = new ObjectMapper().readValue(post, Post.class);
        postMain.setPhoto(Converter.convertByteArrayToBase64(photo.getBytes()));
//        if (post != null) {
            if (photo != null) {
                log.info(photo);
            return ResponseEntity.ok(postService.save(postMain));
        }
        return null;
    }

//    @PostMapping
//    public ResponseEntity<Post> create(@RequestParam(value = "photo", required = false) MultipartFile photo,
//                                              @RequestParam("post") String post) throws IOException {
//
//        post = post.replace("\"photo\":{},", "");
//        Post postMain = new ObjectMapper().readValue(post, Post.class);
//        if (photo != null) {
//            postMain.setPhoto(Converter.convertByteArrayToBase64(photo.getBytes()));
//        }
//        postMain = postService.create(dto);
//        if (postMain != null) {
//            return new ResponseEntity<>(dto, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

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
