package org.example.service;

import org.example.exception.NotFoundException;
import org.example.tables.Room;

import java.util.List;

public interface RoomServiceInt {
    List<Room> readAll();
    Room findById(int elementId);
    List<Room> findByRate(int rate);
    void update(Room element);
    void delete(int elementId);
    void add(Room element);
}
