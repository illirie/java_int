package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;

import java.util.List;

public interface Service <T, K> {
    List<T> findAll();
    T findById(K id) throws NotFoundException;
    void save(T t);
    void deleteById(K id);
    void update(T t);
}
