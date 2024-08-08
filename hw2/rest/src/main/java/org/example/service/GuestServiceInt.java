package org.example.service;

import org.example.tables.Booking;
import org.example.tables.Guest;

import java.util.List;

public interface GuestServiceInt {
    List<Guest> readAll();
    Guest findById(int elementId);
    void update(Guest element);
    void delete(int elementId);
    void add(Guest element);
    int findGuestRoom(Guest element);
}
