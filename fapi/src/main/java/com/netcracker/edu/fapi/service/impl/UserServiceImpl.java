package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.security.JwtUserFactory;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("customUserService")
public class UserServiceImpl implements UserService, UserDetailsService{

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/login/" + login, User.class);
    }

    @Override
    public Boolean existUser(String login) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForObject(backendServerUrl + "/api/users/login/" + login, User.class);
        if (restTemplate.getForObject(backendServerUrl + "/api/users/login/" + login, User.class) != null)
            return true;
        else
            return null;
    }

    @Override
    public Boolean existEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        if (restTemplate.getForObject(backendServerUrl + "/api/users/email/" + email, User.class) != null)
            return true;
        else
            return null;
    }

    @Override
    public User findByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/email/" + email, User.class);
    }

//    @Override
//    public User findById(long id) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(backendServerUrl + "/api/users/" + id, User.class);
//    }

    @Override
    public List<User> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/users/", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public User save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        user.setCreated(time);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return restTemplate.postForEntity(backendServerUrl + "/api/users/", user, User.class).getBody();
    }

    @Override
    public User update(User user) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "api/users/", user);
        return findByLogin(user.getLogin());
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return JwtUserFactory.create(user);
    }
}
