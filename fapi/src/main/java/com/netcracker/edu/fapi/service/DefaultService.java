package com.netcracker.edu.fapi.service;

import java.util.Optional;

public interface DefaultService<T> {
    Optional<T> getById(Long id);
    Iterable<T> getAll();
    T save(T entity);
    void delete(Long id);
}
