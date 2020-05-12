package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Ban;
import com.netcracker.edu.backend.repository.BanRepository;
import com.netcracker.edu.backend.service.BanService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BanServiceImpl implements BanService {

    final
    BanRepository banRepository;

    public BanServiceImpl(BanRepository banRepository) {
        this.banRepository = banRepository;
    }

    @Override
    public Optional<Ban> getById(Long id) {
        return banRepository.findById(id);
    }

    @Override
    public Iterable<Ban> getAll() {
        return banRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        banRepository.deleteById(id);
    }

    @Override
    public Ban save(Ban ban) {
        return banRepository.save(ban);
    }

    @Override
    public Ban update(Ban entity) {
        return null;
    }
}
