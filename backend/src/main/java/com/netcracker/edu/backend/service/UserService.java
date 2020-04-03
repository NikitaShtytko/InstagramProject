package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUsersById(Long id);

    Iterable<User> getAllUsers();

    void deleteUsers(Long id);

    User saveUsers(User user);

    User findByLogin(String login);
}
