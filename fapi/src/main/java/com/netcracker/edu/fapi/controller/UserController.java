package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers(){
        System.out.println("");
        return (List<User>) userService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User getUserById(@PathVariable(name = "id") long id){
//        return userService.findById(id);
//    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public User getUserByLogin(@PathVariable(name = "login") String login) {
        return userService.findByLogin(login);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/login/exist/{login}")
    public String ExistUser(@PathVariable String login) {
        User user = userService.existUser(login);
        return user.getLogin();
    }
}
