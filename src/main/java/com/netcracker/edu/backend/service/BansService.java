package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Bans;

import java.util.Optional;

public interface BansService {
    Optional<Bans> getById(Long id);
}
