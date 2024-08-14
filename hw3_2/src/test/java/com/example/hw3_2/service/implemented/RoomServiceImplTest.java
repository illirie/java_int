package com.example.hw3_2.service.implemented;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.model.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceImplTest {
    private RoomServiceImpl roomService = new RoomServiceImpl();

    @Test
    void findAll() {
        List<Room> guests = roomService.findAll();
        for (Room guest : guests) {
            System.out.println(guest);
        }
    }

    @Test
    void findById() {
        try {
            Room room = roomService.findById(1);
            System.out.println(room);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void save() {
        try {
            Rate rate = new RateServiceImpl().findById(1);
            Room room = new Room(rate);
            roomService.save(room);
        }
        catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteById() {
        roomService.deleteById(1);
        try {
            Room guest = roomService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
        try {
            Rate rate = new RateServiceImpl().findById(2);
            Room room = roomService.findById(1);
            room.setRate(rate);
            roomService.update(room);
        }
        catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}