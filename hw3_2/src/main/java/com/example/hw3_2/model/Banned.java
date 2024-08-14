package com.example.hw3_2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Banned extends Person {
    @Column(name = "banned_by")
    private String bannedBy;

    public Banned() {}

    public Banned(String first_name, String last_name, String phone, String email, String bannedBy) {
        super(first_name, last_name, phone, email);
        this.bannedBy = bannedBy;
    }

    public String getBannedBy() {
        return bannedBy;
    }
    public void setBannedBy(String bannedBy) {
        this.bannedBy = bannedBy;
    }
}
