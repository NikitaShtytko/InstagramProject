package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Users;

import java.util.Optional;

public interface UsersService {

    Optional<Users> getUsersById(Long id);

    Users findByLogin(String login);

    Iterable<Users> getAllUsers();

    void deleteUsers(Long id);

    Users saveUsers(Users users);
}
