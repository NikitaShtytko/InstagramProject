package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;

public interface UserService extends DefaultService<User>{
    User findByLogin(String login);
    User existUser(String login);
    User findByEmail(String email);
//    User findById(long id);
}
