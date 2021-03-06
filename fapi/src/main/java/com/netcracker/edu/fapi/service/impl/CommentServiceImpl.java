package com.netcracker.edu.fapi.service.impl;


import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Iterable<Comment> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comment = restTemplate.getForObject(backendServerUrl + "api/comments/", Comment[].class);
        return comment == null ? Collections.emptyList() : Arrays.asList(comment);
    }

    @Override
    public Comment getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/comments/" + id, Comment.class);
    }

    @Override
    public Iterable<Comment> findCommentsByPostId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comment = restTemplate.getForObject(backendServerUrl + "api/comments/post/" + id, Comment[].class);
        return comment == null ? Collections.emptyList() : Arrays.asList(comment);
    }

    @Override
    public Comment save(Comment entity) {
        RestTemplate restTemplate = new RestTemplate();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        entity.setDate(time);
        return restTemplate.postForEntity(backendServerUrl + "api/comments/", entity, Comment.class).getBody();
    }

    @Override
    public Comment update(Comment entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "api/comments/" + id);
    }


}
