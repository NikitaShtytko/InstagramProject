package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

public interface UserService extends DefaultService<User>{
    User findByLogin(String login);
    User findByEmail(String email);
}
