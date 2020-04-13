package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public User findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/login/" + login, User.class);
    }

    @Override
    public List<User> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/users/", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public User save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/users/", user, User.class).getBody();
    }


//    @Override
//    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByLogin(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
//    }
//
//    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//        return authorities;
//    }

    @Override
    public void delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + id);
    }

    @Override
    public User getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/" + id, User.class);
    }
}
