package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Likes;
import com.netcracker.edu.backend.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/like")
public class LikesController {

    private LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Likes> getLikesById(@PathVariable(name = "id") Long id) {
        Optional<Likes> likes = likesService.getLikesById(id);
        if (likes.isPresent()) {
            return ResponseEntity.ok(likes.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Likes> getAllLikes() {
        System.out.println("GET DETECTED");
        return likesService.getAllLikes();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Likes saveLikes(@RequestBody Likes likes) {
        return likesService.saveLikes(likes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLikes(@PathVariable(name = "id") Long id) {
        likesService.deleteLikes(id);
    }
}
