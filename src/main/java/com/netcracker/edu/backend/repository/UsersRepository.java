package com.netcracker.edu.backend.repository;


import com.netcracker.edu.backend.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
}
