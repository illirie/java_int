package org.example.service;

import org.example.database.ConnectionManager;
import org.example.tables.Guest;
import org.example.tables.Rate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestService implements GuestServiceInt{

    private static ConnectionManager connectionManager;
    static {
        try {
            connectionManager = ConnectionManager.getInstance();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String readAll_SQL = """
            SELECT * from guests;
            """;

    private static String findById_SQL = """
            SELECT * from guests WHERE guest_id =
            """;

    private static String insert_SQL = """
            INSERT INTO guests (first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?);
            """;

    private static String delete_SQL = """
            DELETE from guests WHERE guest_id =
            """;

    private static String update_SQL = """
            UPDATE guests SET first_name=?, last_name=?, phone_number=?, email=?
            WHERE guest_id=?;
            """;

    private static String findRoom_SQL = """
            SELECT room from booking WHERE guest=
            """;

    @Override
    public List<Guest> readAll() {
        Statement statement = null;
        List<Guest> guests = new ArrayList<>();
        try {
            statement = connectionManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAll_SQL);
            while (resultSet.next()) {
                guests.add(new Guest(resultSet));
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guests;
    }

    @Override
    public Guest findById(int elementId) {
        ResultSet resultSet;
        Statement statement = null;
        try {
            statement = connectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(findById_SQL + elementId + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Guest(resultSet);
    }

    @Override
    public void update(Guest element) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(update_SQL);
            preparedStatement.setNString(1, element.getFirstName());
            preparedStatement.setNString(2, element.getLastName());
            preparedStatement.setNString(3, element.getPhone());
            preparedStatement.setNString(4, element.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int elementId) {
        Statement statement = null;
        try {
            statement = connectionManager.getConnection().createStatement();
            statement.executeQuery(delete_SQL + elementId + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Guest element) {
        PreparedStatement statement = null;
        try {
            statement = connectionManager.getConnection().prepareStatement(insert_SQL);
            statement.setNString(1, element.getFirstName());
            statement.setNString(2, element.getLastName());
            statement.setNString(3, element.getPhone());
            statement.setNString(4, element.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findGuestRoom(Guest element) {
        ResultSet resultSet;
        int res;
        Statement statement = null;
        try {
            statement = connectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(findRoom_SQL + element.getId() + ";");
            res = resultSet.getInt("room");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
