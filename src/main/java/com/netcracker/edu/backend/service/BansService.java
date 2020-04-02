package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Bans;

import java.util.Optional;

public interface BansService {

    Optional<Bans> getBansById(Long id);

    Iterable<Bans> getAllBans();

    Bans saveBans(Bans bans);

    void deleteBans(Long id);
}
