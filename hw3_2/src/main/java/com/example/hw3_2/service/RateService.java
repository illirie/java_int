package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.model.Room;

import java.util.List;

public interface RateService extends Service <Rate, Integer> {
    Rate findById(Integer id) throws NotFoundException;
    List<Rate> findAll();
    void save(Rate rate);
    void deleteById(Integer id);
    void update(Rate rate);
}
