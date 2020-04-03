package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Bans;
import com.netcracker.edu.backend.repository.BansRepository;
import com.netcracker.edu.backend.service.BansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BansServiceImpl implements BansService {
    @Autowired
    BansRepository bansRepository;

    @Override
    public Optional<Bans> getBansById(Long id) {
        return bansRepository.findById(id);
    }

    @Override
    public Iterable<Bans> getAllBans() {
        return bansRepository.findAll();
    }

    @Override
    public void deleteBans(Long id) {
        bansRepository.deleteById(id);
    }

    @Override
    public Bans saveBans(Bans bans) {
        return bansRepository.save(bans);
    }
}
