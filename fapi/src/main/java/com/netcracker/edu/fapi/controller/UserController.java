package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userService.getAll();
    }

//    @RequestMapping(value="/signup", method = RequestMethod.POST, produces = "application/json")
//    public User saveUser(@RequestBody User user){
//        return userService.save(user);
//    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public User getUserByLogin(@PathVariable(name = "login") String login) {
        return userService.findByLogin(login);
    }
}
