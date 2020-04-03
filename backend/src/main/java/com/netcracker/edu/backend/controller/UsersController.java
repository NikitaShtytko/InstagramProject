package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Users;
import com.netcracker.edu.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")

public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUserByLogin(@PathVariable(name = "login") String login) {
        Users users = usersService.findByLogin(login);
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUsersById(@PathVariable(name = "id") Long id) {
        Optional<Users> users = usersService.getUsersById(id);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Users> getAllUsers() {
        System.out.println("GET DETECTED");
        return usersService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Users saveUsers(@RequestBody Users users) {
        return usersService.saveUsers(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUsers(@PathVariable(name = "id") Long id) {
        usersService.deleteUsers(id);
    }
}
