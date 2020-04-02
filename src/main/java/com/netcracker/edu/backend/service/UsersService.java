package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Users;

import java.util.Optional;

public interface UsersService {
    Optional<Users> getById(Long id);

    Optional<Users> getUsersById(Long id);

    Iterable<Users> getAllUsers();

    void deleteUsers(Long id);

    Users saveUsers(Users users);
}
