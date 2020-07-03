package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.util.Converter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> saveUser(@RequestParam(value = "photo", required = false) MultipartFile photo,
                                         @RequestParam("user") String user) throws IOException {
        user = user.replace("\"photo\":{},", "");
        User userInfo = new ObjectMapper().readValue(user, User.class);
        if (photo != null) {
            userInfo.setPhoto(Converter.convertByteArrayToBase64(photo.getBytes()));
        }
        return ResponseEntity.ok(userService.update(userInfo));
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public User getUserByLogin(@PathVariable(name = "login") String login) {
        User user = userService.findByLogin(login);
        return user;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/login/exist/{login}")
    public Boolean ExistUser(@PathVariable String login) {
        Boolean user = userService.existUser(login);
        return user;
    }

    @GetMapping("/email/exist/{email}")
    public Boolean ExistEmail(@PathVariable String email) {
        Boolean userEmail = userService.existEmail(email);
        return userEmail;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id) {
        userService.delete(Long.valueOf(id));
    }
}
