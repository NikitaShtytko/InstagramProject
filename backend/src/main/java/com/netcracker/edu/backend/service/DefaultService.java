package com.netcracker.edu.backend.service;

import java.util.Optional;

public interface DefaultService<T> {
    Optional<T> getById(Long id);
    Iterable<T> getAll();
    T save(T entity);
    T update(T entity);
    void delete(Long id);
}
