package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Tag;
import com.netcracker.edu.backend.repository.TagRepository;
import com.netcracker.edu.backend.service.TagService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    final
    TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public Optional<Tag> getTagsById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag saveTags(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTags(Long id) {
        tagRepository.deleteById(id);
    }
}
