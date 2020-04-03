package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Ban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends CrudRepository<Ban,Long> {
}
