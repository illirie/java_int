package com.example.model;

public class Rate {
    private int id;
    private int maxPersons;
    private int price;

    public Rate() {
    }

    public Rate(int id, int maxPersons, int price) {
        this.id = id;
        this.maxPersons = maxPersons;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getMaxPersons() {
        return maxPersons;
    }
    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rate [id=" + id + ", maxPersons=" + maxPersons + ", price=" + price + "]";
    }
}
