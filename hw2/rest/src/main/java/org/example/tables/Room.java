package org.example.tables;

/*
Комната отеля.
Реализация связи many-to-many через таблицу Booking и
связи one-to-many с таблицей Rates.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    private int id;
    private int floor;
    private int rate;

    public Room() {
        id = 0;
        floor = 1;
        rate = 1;
    }

    public Room(int id, int floor, int rate) {
        this.id = id;
        this.floor = floor;
        this.rate = rate;
    }

    public Room(ResultSet resultSet) {
        try {
            id = resultSet.getInt("room_id");
            floor = resultSet.getInt("room_floor");
            rate = resultSet.getInt("rate");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", floor=" + floor + ", rate=" + rate + '\'' + '}';
    }
}
