package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.repository.RoleRepository;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> getRolesById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Iterable<Role> getAllRoles(){

        return roleRepository.findAll(); }

    @Override
    public Role saveRoles(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoles(Long id) {
        roleRepository.deleteById(id); }
}
