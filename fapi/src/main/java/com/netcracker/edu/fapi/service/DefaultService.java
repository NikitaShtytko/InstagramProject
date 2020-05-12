package com.netcracker.edu.fapi.service;

public interface DefaultService<T> {
    T getById(Long id);
    Iterable<T> getAll();
    T save(T entity);
    T update(T entity);
    void delete(Long id);
}
