package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Room;

import java.util.List;

public interface RoomService extends Service<Room, Integer> {
    @Override
    List<Room> findAll();
    Room findById(Integer id) throws NotFoundException;
    void save(Room room);
    void deleteById(Integer id);
    void update(Room room);
}
