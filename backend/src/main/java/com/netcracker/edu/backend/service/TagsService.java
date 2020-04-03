package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Tags;

import java.util.Optional;

public interface TagsService {
    Optional<Tags> getTagsById(Long id);

    Iterable<Tags> getAllTags();

    Tags saveTags(Tags tags);

    void deleteTags(Long id);
}
