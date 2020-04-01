package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Users;

import java.util.Optional;

public interface UserService{
    Optional<Users> getById(Long id);
}
