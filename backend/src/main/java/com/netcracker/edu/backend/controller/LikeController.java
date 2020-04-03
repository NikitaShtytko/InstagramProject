package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Like> getLikesById(@PathVariable(name = "id") Long id) {
        Optional<Like> likes = likeService.getLikesById(id);
        return likes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Like> getAllLikes() {
        System.out.println("GET DETECTED");
        return likeService.getAllLikes();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Like saveLikes(@RequestBody Like like) {
        return likeService.saveLikes(like);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLikes(@PathVariable(name = "id") Long id) {
        likeService.deleteLikes(id);
    }
}
