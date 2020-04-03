package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Ban;
import com.netcracker.edu.backend.repository.BanRepository;
import com.netcracker.edu.backend.service.BanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BanServiceImpl implements BanService {
    @Autowired
    BanRepository banRepository;

    @Override
    public Optional<Ban> getBansById(Long id) {
        return banRepository.findById(id);
    }

    @Override
    public Iterable<Ban> getAllBans() {
        return banRepository.findAll();
    }

    @Override
    public void deleteBans(Long id) {
        banRepository.deleteById(id);
    }

    @Override
    public Ban saveBans(Ban ban) {
        return banRepository.save(ban);
    }
}
