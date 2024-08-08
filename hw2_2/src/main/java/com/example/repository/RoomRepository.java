package com.example.repository;

import com.example.model.Room;

public interface RoomRepository extends Repository<Room, Integer> {
    boolean isRoomEmpty(Integer id);
}
