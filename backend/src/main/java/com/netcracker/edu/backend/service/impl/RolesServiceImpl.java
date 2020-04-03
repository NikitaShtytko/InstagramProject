package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Roles;
import com.netcracker.edu.backend.repository.RolesRepository;
import com.netcracker.edu.backend.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Optional<Roles> getRolesById(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Iterable<Roles> getAllRoles(){return rolesRepository.findAll(); }

    @Override
    public Roles saveRoles(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void deleteRoles(Long id) {rolesRepository.deleteById(id); }
}
