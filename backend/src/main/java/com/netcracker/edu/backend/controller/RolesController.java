package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Roles;
import com.netcracker.edu.backend.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RolesController {

    private RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService){this.rolesService = rolesService;}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Roles> getRolesById(@PathVariable(name = "id") Long id) {
        Optional<Roles> roles = rolesService.getRolesById(id);
        if (roles.isPresent()) {
            return ResponseEntity.ok(roles.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Roles> getAllRoles() {
        System.out.println("GET DETECTED");
        return rolesService.getAllRoles();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Roles saveRoles(@RequestBody Roles roles) {
        return rolesService.saveRoles(roles);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRoles(@PathVariable(name = "id") Long id) {
        rolesService.deleteRoles(id);
    }
}
