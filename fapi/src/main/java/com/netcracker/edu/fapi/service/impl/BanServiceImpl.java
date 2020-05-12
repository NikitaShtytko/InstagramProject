package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Ban;
import com.netcracker.edu.fapi.service.BanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class BanServiceImpl implements BanService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public Iterable<Ban> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Ban[] ban = restTemplate.getForObject(backendServerUrl + "/api/bans/", Ban[].class);
        return ban == null ? Collections.emptyList() : Arrays.asList(ban);
    }

    @Override
    public Ban getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/bans/" + id, Ban.class);
    }

    @Override
    public Ban save(Ban entity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/bans/", entity, Ban.class).getBody();
    }

    @Override
    public Ban update(Ban entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/bans/", id);
    }
}
