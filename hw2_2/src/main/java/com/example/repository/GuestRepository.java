package com.example.repository;

import com.example.model.Guest;

public interface GuestRepository extends Repository<Guest, Integer> {
    boolean hasBooking(Integer id);
}
