package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts,Long> {
}
