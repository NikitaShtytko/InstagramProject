package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Tag;
import com.netcracker.edu.fapi.service.TagService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

public class TagServiceImpl implements TagService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Iterable<Tag> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Tag[] tag = restTemplate.getForObject(backendServerUrl + "/api/tags/", Tag[].class);
        return tag == null ? Collections.emptyList() : Arrays.asList(tag);
    }

    @Override
    public Tag getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/tags/" + id, Tag.class);
    }

    @Override
    public Tag save(Tag account) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/tags", account, Tag.class).getBody();
    }

    @Override
    public void delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/tags/" + id);
    }
}