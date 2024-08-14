package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Banned;

import java.util.List;

public interface BannedService extends Service<Banned, Integer>{
    List<Banned> findAll();
    Banned findById(Integer id) throws NotFoundException;
    void deleteById(Integer id);
    void update(Banned banned);
    void save(Banned banned);
}
