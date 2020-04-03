package com.netcracker.edu.backend.service.impl;


import com.netcracker.edu.backend.entity.Users;
import com.netcracker.edu.backend.repository.UsersRepository;
import com.netcracker.edu.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Optional<Users> getById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<Users> getUsersById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users saveUsers(Users users) {
        return null;
    }
}
