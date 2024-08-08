package org.example.service;

import org.example.exception.NotFoundException;
import org.example.tables.Rate;

import java.util.List;

public interface RateServiceInt {
    List<Rate> readAll();
    Rate findById(int elementId);
    void update(Rate element);
    void delete(int elementId);
    void add(Rate element);
}
