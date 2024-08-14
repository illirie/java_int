package com.example.hw3_2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rates")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private long rateId;
    @Column(name = "price")
    private double price;
    @Column(name = "max_persons")
    private int maxPersons;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate")
    private List<Room> rooms;

    public Rate() {
    }

    public Rate(double price, int maxPersons) {
        this.price = price;
        this.maxPersons = maxPersons;
        this.rooms = null;
    }

    public Rate(int id, double price, int maxPersons) {
        this.rateId = id;
        this.price = price;
        this.maxPersons = maxPersons;
        this.rooms = null;
    }

    public long getRateId() {
        return rateId;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getMaxPersons() {
        return maxPersons;
    }
    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return "Rate [rateId=" + rateId + ", price=" + price + ", maxPersons=" + maxPersons + "rooms=" + rooms +  "]";
    }
}
