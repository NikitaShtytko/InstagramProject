package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Tags;
import com.netcracker.edu.backend.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tag")
public class TagsController {

    private TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService){this.tagsService = tagsService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tags> getTagsById(@PathVariable(name = "id") Long id) {
        Optional<Tags> tags = tagsService.getTagsById(id);
        if (tags.isPresent()) {
            return ResponseEntity.ok(tags.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Tags> getAllTags() {
        System.out.println("GET DETECTED");
        return tagsService.getAllTags();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Tags saveTags(@RequestBody Tags tag) {
        return tagsService.saveTags(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTags(@PathVariable(name = "id") Long id) {
        tagsService.deleteTags(id);
    }
}
