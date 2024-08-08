package com.example.repository.implemented;

import com.example.db.ConnectionManager;
import com.example.db.ConnectionManagerImpl;
import com.example.exception.RepositoryException;
import com.example.model.Guest;
import com.example.model.Room;
import com.example.repository.RoomRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private static RoomRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    private static String add_SQL = """
            INSERT INTO rooms (rate)
            VALUES (?);
            """;

    private static String update_SQL = """
            UPDATE rooms
            SET rate = ?
            WHERE room_id = ?;
            """;

    private static String delete_SQL = """
            DELETE FROM rooms
            WHERE room_id = ?;
            """;

    private static String findAll_SQL = """
            SELECT room_id, rate
            FROM rooms;
            """;

    private static String findById_SQL = """
            SELECT room_id, rate
            FROM rooms WHERE id = ?;
            """;

    private static String existsById_SQL = """
            SELECT exists (
            SELECT 1
            FROM rooms
            WHERE room_id = ?
            LIMIT 1);
            """;
    private static String isEmpty_SQL = """
            SELECT exists (
            SELECT 1
            FROM booking
            WHERE room_id = ?
            LIMIT 1);
        """;

    private RoomRepositoryImpl() {}

    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepositoryImpl();
        }
        return instance;
    }

    private static Room createRoom(ResultSet rs) throws SQLException {
        return new Room(
                rs.getInt("room_id"),
                rs.getInt("rate")
        );
    }

    @Override
    public boolean isRoomEmpty(Integer id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(isEmpty_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return isExists;
    }

    @Override
    public Room add(Room room) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(add_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, room.getRate());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                room = new Room(
                        resultSet.getInt("room_id"),
                        room.getRate()
                );
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return room;
    }

    @Override
    public void update(Room room) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update_SQL);) {
            preparedStatement.setInt(1, room.getRate());;
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean deleteResult = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete_SQL);) {
            preparedStatement.setInt(1, id);
            deleteResult = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return deleteResult;
    }

    @Override
    public Optional<Room> findById(Integer id) {
        Room room = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findById_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = createRoom(resultSet);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> findAll() {
        List<Room> roomList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAll_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roomList.add(createRoom(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return roomList;
    }

    @Override
    public boolean exitsById(Integer id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(existsById_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return isExists;
    }
}
