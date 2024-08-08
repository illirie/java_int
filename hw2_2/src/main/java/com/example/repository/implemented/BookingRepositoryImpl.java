package com.example.repository.implemented;

import com.example.db.ConnectionManager;
import com.example.db.ConnectionManagerImpl;
import com.example.exception.RepositoryException;
import com.example.model.Booking;
import com.example.model.Rate;
import com.example.repository.BookingRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {
    private static BookingRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    private static String add_SQL = """
            INSERT INTO booking (guest_id, room_id, start_date, end_date) 
            VALUES (?, ?, ?, ?);
            """;

    private static String update_SQL = """
            UPDATE booking 
            SET guest_id = ?, room_id = ?, start_date = ?, end_date = ?
            WHERE booking_id = ?;
            """;

    private static String delete_SQL = """
            DELETE FROM booking
            WHERE booking_id = ?;
            """;

    private static String findById_SQL = """
            SELECT booking_id, guest_id, room_id, start_date, end_date
            FROM booking 
            WHERE booking_id = ?;
            """;

    private static String findAll_SQL = """
            SELECT booking_id, guest_id, room_id, start_date, end_date FROM booking;
            """;

    private static String existsById_SQL = """
            SELECT exists (
            SELECT 1
            FROM booking
            WHERE booking_id = ?
            LIMIT 1);
            """;

    private BookingRepositoryImpl() {}

    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepositoryImpl();
        }
        return instance;
    }

    public static Booking createBooking(ResultSet rs) throws SQLException {
        return new Booking(
                rs.getInt("booking_id"),
                rs.getInt("guest_id"),
                rs.getInt("room_id"),
                rs.getDate("start_date"),
                rs.getDate("end_date")
        );
    }

    @Override
    public Booking add(Booking booking) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(add_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, booking.getGuestId());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setDate(3, booking.getStartTime());
            preparedStatement.setDate(4, booking.getEndTime());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                booking = new Booking(
                        resultSet.getInt("booking_id"),
                        booking.getGuestId(),
                        booking.getRoomId(),
                        booking.getStartTime(),
                        booking.getEndTime()
                );
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return booking;
    }

    @Override
    public void update(Booking booking) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update_SQL);) {
            preparedStatement.setInt(1, booking.getGuestId());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setDate(3, booking.getStartTime());
            preparedStatement.setDate(4, booking.getEndTime());
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
    public Optional<Booking> findById(Integer id) {
        Booking booking = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findById_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = createBooking(resultSet);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return Optional.ofNullable(booking);
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAll_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookingList.add(createBooking(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return bookingList;
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
