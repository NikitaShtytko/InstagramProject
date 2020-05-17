package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;

@Service
public class PostServiceImpl implements PostService{

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Iterable<Post> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] post = restTemplate.getForObject(backendServerUrl + "/api/posts/", Post[].class);
        return post == null ? Collections.emptyList() : Arrays.asList(post);
    }

    @Override
    public Post getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/posts/" + id, Post.class);
    }

    @Override
    public Post save(Post entity) {
        RestTemplate restTemplate = new RestTemplate();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        entity.setDate(time);
        return restTemplate.postForEntity(backendServerUrl + "/api/posts/", entity, Post.class).getBody();
    }

    @Override
    public Post update(Post entity) {
        RestTemplate restTemplate = new RestTemplate();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        entity.setDate(time);
        restTemplate.put(backendServerUrl + "/api/posts/", entity);
        return getById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/posts/" + id);
    }

    @Override
    public Iterable<Post> findByUserId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Post[] post = restTemplate.getForObject(backendServerUrl + "/api/posts/user/" + id, Post[].class);
        return post == null ? Collections.emptyList() : Arrays.asList(post);
    }
}