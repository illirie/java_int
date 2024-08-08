package org.example.service;

import org.example.database.ConnectionManager;
import org.example.exception.NotFoundException;
import org.example.tables.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomService implements RoomServiceInt {

    private static ConnectionManager connectionManager;
    static {
        try {
            connectionManager = ConnectionManager.getInstance();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String readAll_SQL = """
            SELECT * from rooms;
            """;

    private static String findById_SQL = """
            SELECT * from rooms WHERE room_id = 
            """;

    private static String findByRate_SQL = """
            SELECT * from rooms WHERE rate = 
            """;

    private static String insert_SQL = """
            INSERT INTO rooms (room_floor, rate) VALUES (?, ?);
            """;

    private static String delete_SQL = """
            DELETE from rooms WHERE room_id = 
            """;

    private static String update_SQL = """
            UPDATE rooms SET room_floor=?, rate=?
            WHERE room_id=?;
            """;

    @Override
    public List<Room> readAll() {
        Statement statement = null;
        List<Room> rooms = new ArrayList<Room>();
        try {
            statement = connectionManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAll_SQL);
            while (resultSet.next()) {
                rooms.add(new Room(resultSet));
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public Room findById(int elementId) {
        Statement statement = null;
        ResultSet resultSet;
        try {
            statement = connectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(findById_SQL + elementId + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Room(resultSet);
    }

    @Override
    public List<Room> findByRate(int rate) {
        Statement statement = null;
        List<Room> rooms = new ArrayList<Room>();
        try {
            statement = connectionManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(findByRate_SQL + rate + ";");
            while (resultSet.next()) {
                rooms.add(new Room(resultSet));
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public void update(Room element) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(update_SQL);
            preparedStatement.setInt(1, element.getFloor());
            preparedStatement.setInt(2, element.getRate());
            preparedStatement.setInt(3, element.getId());
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
    public void add(Room element) {
        PreparedStatement statement = null;
        try {
            statement = connectionManager.getConnection().prepareStatement(insert_SQL);
            statement.setInt(1, element.getFloor());
            statement.setInt(2, element.getRate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
