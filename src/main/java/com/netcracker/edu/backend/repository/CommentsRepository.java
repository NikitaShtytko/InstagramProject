package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments,Long> {
}
