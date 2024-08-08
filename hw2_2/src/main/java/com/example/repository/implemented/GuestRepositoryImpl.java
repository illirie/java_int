package com.example.repository.implemented;

import com.example.db.ConnectionManager;
import com.example.db.ConnectionManagerImpl;
import com.example.exception.RepositoryException;
import com.example.model.Guest;
import com.example.repository.GuestRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepositoryImpl implements GuestRepository {

    private static GuestRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    private static String add_SQL = """
            INSERT INTO guests (first_name, last_name, phone, email)
            VALUES (?, ?, ?, ?);
            """;

    private static String update_SQL = """
            UPDATE guests
            SET first_name = ?, last_name = ?, phone = ?, email = ?
            WHERE guest_id = ?;
            """;

    private static String delete_SQL = """
            DELETE FROM guests
            WHERE guest_id = ?;
            """;

    private static String findAll_SQL = """
            SELECT guest_id, first_name, last_name, phone, email
            FROM guests;
            """;

    private static String findById_SQL = """
            SELECT guest_id, first_name, last_name, phone, email
            FROM guests WHERE id = ?;
            """;

    private static String existsById_SQL = """
            SELECT exists (
            SELECT 1
            FROM guests
            WHERE guest_id = ?
            LIMIT 1);
            """;
    private static String hasBooking_SQL = """
            SELECT exists (
            SELECT 1
            FROM booking
            WHERE guest_id = ?
            LIMIT 1);
        """;

    private GuestRepositoryImpl() {}

    public static GuestRepository getInstance() {
        if (instance == null) {
            instance = new GuestRepositoryImpl();
        }
        return instance;
    }

    private static Guest createGuest(ResultSet rs) throws SQLException {
        return new Guest(
                rs.getInt("guest_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }

    @Override
    public boolean hasBooking(Integer id) {
        boolean isExists = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(hasBooking_SQL)) {
            preparedStatement.setInt(1, id);
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
    public Guest add(Guest guest) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(add_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setString(3, guest.getPhone());
            preparedStatement.setString(4, guest.getEmail());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                guest = new Guest(
                        resultSet.getInt("guest_id"),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getPhone(),
                        guest.getEmail()
                );
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return guest;
    }

    @Override
    public void update(Guest guest) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update_SQL);) {
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setString(3, guest.getPhone());
            preparedStatement.setString(4, guest.getEmail());
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
    public Optional<Guest> findById(Integer id) {
        Guest guest = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findById_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                guest = createGuest(resultSet);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return Optional.ofNullable(guest);
    }

    @Override
    public List<Guest> findAll() {
        List<Guest> guestList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAll_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guestList.add(createGuest(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return guestList;
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
