package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;

public interface UserService extends DefaultService<User>{
    User findByLogin(String login);
    Boolean existUser(String login);
    Boolean existEmail(String email);
    User findByEmail(String email);
//    User findById(long id);
}
