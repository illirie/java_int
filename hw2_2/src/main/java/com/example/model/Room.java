package com.example.model;

public class Room {
    private int id;
    private int rate;

    public Room() {}

    public Room(int id, int rate) {
        this.id = id;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", rate=" + rate + "]";
    };
}
