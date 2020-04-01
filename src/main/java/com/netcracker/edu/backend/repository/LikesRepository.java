package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Likes;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Likes,Long> {
}
