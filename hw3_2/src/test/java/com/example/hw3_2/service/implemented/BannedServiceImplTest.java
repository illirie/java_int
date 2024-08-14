package com.example.hw3_2.service.implemented;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Banned;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.service.BannedService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BannedServiceImplTest {
    private BannedService bannedService = new BannedServiceImpl();

    @Test
    void findAll() {
        List<Banned> guests = bannedService.findAll();
        for (Banned guest : guests) {
            System.out.println(guest);
        }
    }

    @Test
    void findById() {
        try {
            Banned guest = bannedService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void save() {
        Banned guest = new Banned("Lara", "Norton", "lara@gmail.com", "+7955111111",
                "reason");
        bannedService.save(guest);
    }

    @Test
    void deleteById() {
        bannedService.deleteById(1);
        try {
            Banned guest = bannedService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
        try {
            Banned guest = bannedService.findById(1);
            guest.setBannedBy("second reason");
            bannedService.update(guest);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}