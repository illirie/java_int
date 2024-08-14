package com.example.hw3_2.service.implemented;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.service.RateService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RateServiceImplTest {
    private static RateService rateService = new RateServiceImpl();

    @Test
    void findById() {
        try {
            Rate rate = rateService.findById(1);
            System.out.println(rate);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findAll() {
       List<Rate> rates = rateService.findAll();
       for (Rate rate : rates) {
           System.out.println(rate);
       }
    }

    @Test
    void save() {
        Rate rate = new Rate(63.3, 2);
        rateService.save(rate);
    }

    @Test
    void deleteById() {
        rateService.deleteById(1);
        try {
            rateService.findById(1);
        } catch (NotFoundException e) {
            System.out.println("Delete successfully.");
        }
    }

    @Test
    void update() {
    }
}