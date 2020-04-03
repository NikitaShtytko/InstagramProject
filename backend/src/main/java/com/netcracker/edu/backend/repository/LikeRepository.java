package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like,Long> {
}
