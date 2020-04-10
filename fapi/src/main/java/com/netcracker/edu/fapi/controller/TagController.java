package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Tag;
import com.netcracker.edu.fapi.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {


    private TagService tagService;

    @RequestMapping
        public ResponseEntity<Iterable<Tag>> getAllTags() {
            return ResponseEntity.ok(tagService.getAll());
        }

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<Tag> saveTag(@RequestBody Tag billingAccount) {
            if (billingAccount != null) {
                return ResponseEntity.ok(tagService.save(billingAccount));
            }
            return null;
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        public void deleteTag(@PathVariable String id) {
            tagService.delete(Long.valueOf(id));
        }

        @RequestMapping(value = "/{id}")
        public ResponseEntity<Tag> getTag(@PathVariable String id) throws InterruptedException {
            Long tagId = Long.valueOf(id);
            return ResponseEntity.ok(tagService.getById(tagId));
        }

}
