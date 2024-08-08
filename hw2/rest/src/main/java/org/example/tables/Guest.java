package org.example.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Guest {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    Guest(int guest_id, String name, String surname, String number, String email_adress) {
        id = guest_id;
        firstName = name;
        lastName = surname;
        phone = number;
        email = email_adress;
    }

    public Guest(ResultSet resultSet) {
        try {
            id = resultSet.getInt("guest_id");
            firstName = resultSet.getNString("first_name");
            lastName = resultSet.getNString("last_name");
            phone = resultSet.getNString("phone_number");
            email = resultSet.getNString("email");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + firstName + " " + lastName + "', phone='"
                + phone + "', email='" + email + '\'' +
                '}';
    }
}
