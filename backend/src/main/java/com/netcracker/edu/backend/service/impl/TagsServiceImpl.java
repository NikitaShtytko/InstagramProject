package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Tags;
import com.netcracker.edu.backend.repository.TagsRepository;
import com.netcracker.edu.backend.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagsRepository tagsRepository;


    @Override
    public Optional<Tags> getTagsById(Long id) {
        return tagsRepository.findById(id);
    }

    @Override
    public Iterable<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public Tags saveTags(Tags tags) {
        return tagsRepository.save(tags);
    }

    @Override
    public void deleteTags(Long id) {
        tagsRepository.deleteById(id);
    }
}
