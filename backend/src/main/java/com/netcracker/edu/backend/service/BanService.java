package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Ban;

import java.util.Optional;

public interface BanService {

    Optional<Ban> getBansById(Long id);

    Iterable<Ban> getAllBans();

    Ban saveBans(Ban ban);

    void deleteBans(Long id);
}
