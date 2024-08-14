package com.example.hw3_2.service.implemented;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.model.Room;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {
    private GuestServiceImpl guestService = new GuestServiceImpl();

    @Test
    void findAll() {
        List<Guest> guests = guestService.findAll();
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }

    @Test
    void findById() {
        try {
            Guest guest = guestService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void save() {
        Guest guest = new Guest("Lara", "Norton", "lara@gmail.com", "+7955111111",
                new Date(2024, 5, 5), new Date(2024, 5, 12));
        guestService.save(guest);
    }

    @Test
    void deleteById() {
        guestService.deleteById(1);
        try {
            Guest guest = guestService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void update() {
        Guest guest = new Guest(1, "Lara", "Norton", "lara@gmail.com", "+7955111111",
                new Date(2024, 5, 5), new Date(2024, 6, 12));
        guestService.update(guest);
        try {
            guest = guestService.findById(1);
            System.out.println(guest);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findAllRooms() {
        try {
            Guest guest = guestService.findById(1);
            System.out.println(guest);
            List<Room> rooms = guestService.findAllRooms(guest);
            for (Room room : rooms) {
                System.out.println(room);
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void addReservation() {
        try {
            Guest guest = guestService.findById(1);
            System.out.println(guest);
            guest.addRoom(new RoomServiceImpl().findById(1));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}