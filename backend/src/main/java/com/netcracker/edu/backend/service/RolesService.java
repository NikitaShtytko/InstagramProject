package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> getRolesById(Long id);

    Iterable<Roles> getAllRoles();

    Roles saveRoles(Roles roles);

    void deleteRoles(Long id);
}
