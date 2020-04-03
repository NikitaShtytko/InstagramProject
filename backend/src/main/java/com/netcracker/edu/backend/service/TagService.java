package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Tag;

import java.util.Optional;

public interface TagService {
    Optional<Tag> getTagsById(Long id);

    Iterable<Tag> getAllTags();

    Tag saveTags(Tag tag);

    void deleteTags(Long id);
}
