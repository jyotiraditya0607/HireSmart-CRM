package com.hiresmart.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    // Create
    T create(T entity);
    
    // Read
    Optional<T> findById(String id);
    List<T> findAll();
    
    // Update
    T update(T entity);
    
    // Delete
    boolean delete(String id);
} 