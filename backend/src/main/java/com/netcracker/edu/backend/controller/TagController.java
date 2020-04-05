package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Tag;
import com.netcracker.edu.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){this.tagService = tagService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tag> getTagsById(@PathVariable(name = "id") Long id) {
        Optional<Tag> tags = tagService.getById(id);
        return tags.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Tag> getAllTags() {
        System.out.println("GET DETECTED");
        return tagService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Tag saveTags(@RequestBody Tag tag) {
        return tagService.save(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTags(@PathVariable(name = "id") Long id) {
        tagService.delete(id);
    }
}
