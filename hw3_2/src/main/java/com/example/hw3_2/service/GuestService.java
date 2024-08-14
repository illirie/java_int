package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.model.Person;
import com.example.hw3_2.model.Room;

import java.util.List;

public interface GuestService extends Service <Guest, Integer> {
    List<Guest> findAll();
    Guest findById(Integer id) throws NotFoundException;
    void save(Guest guest);
    void deleteById(Integer id);
    void update(Guest guest);
    List<Room> findAllRooms(Guest guest);
    void addReservation(Guest guest, Room room);
    List<Person> findAllPersonsByName(String name);
}
