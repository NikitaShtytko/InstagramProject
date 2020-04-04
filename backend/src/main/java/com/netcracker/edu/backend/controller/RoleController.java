package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){this.roleService = roleService;}

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Role> getRolesById(@PathVariable(name = "id") Long id) {
//        Optional<Role> roles = roleService.getRolesById(id);
//        return roles.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
////    @GetMapping
//    public Iterable<Role> getAllRoles() {
//        System.out.println("GET DETECTED");
//        return roleService.getAllRoles();
//    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Role saveRoles(@RequestBody Role role) {
        System.out.println("POST DETECTED");
        return roleService.saveRoles(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRoles(@PathVariable(name = "id") Long id) {
        roleService.deleteRoles(id);
    }
}
