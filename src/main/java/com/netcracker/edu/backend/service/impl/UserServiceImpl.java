package com.netcracker.edu.backend.service.impl;


import com.netcracker.edu.backend.entity.Users;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Users> getById(Long id) {
        return userRepository.findById(id);
    }
}
