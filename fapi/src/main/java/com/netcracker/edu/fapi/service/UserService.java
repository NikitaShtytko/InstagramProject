package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;

public interface UserService extends DefaultService<User>{
    User findByLogin(String login);
//    User findById(long id);
}
