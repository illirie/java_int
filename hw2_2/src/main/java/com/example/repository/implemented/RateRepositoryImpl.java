package com.example.repository.implemented;

import com.example.db.ConnectionManager;
import com.example.db.ConnectionManagerImpl;
import com.example.exception.RepositoryException;
import com.example.model.Rate;
import com.example.repository.RateRepository;
import com.sun.source.tree.SynchronizedTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RateRepositoryImpl implements RateRepository {
    private static RateRepository instance;
    private final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    private static String add_SQL = """
            INSERT INTO rates (max_persons, price) 
            VALUES (?, ?);
            """;

    private static String update_SQL = """
            UPDATE rates 
            SET max_persons = ?, price = ? 
            WHERE rate_id = ?;
            """;

    private static String delete_SQL = """
            DELETE FROM rates
            WHERE rate_id = ?;
            """;

    private static String findById_SQL = """
            SELECT rate_id, max_persons, price 
            FROM rates 
            WHERE rate_id = ?;
            """;

    private static String findAll_SQL = """
            SELECT rate_id, max_persons, price FROM rates;
            """;

    private static String existsById_SQL = """
            SELECT exists (
            SELECT 1
            FROM rates
            WHERE rate_id = ?
            LIMIT 1);
            """;

    private RateRepositoryImpl() {}

    public static synchronized RateRepository getInstance() {
        if (instance == null) {
            instance = new RateRepositoryImpl();
        }
        return instance;
    }

    private static Rate createRate(ResultSet rs) throws SQLException {
        return new Rate(
                rs.getInt("rate_id"),
                rs.getInt("max_persons"),
                rs.getInt("price")
        );
    }

    @Override
    public Rate add(Rate rate) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(add_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, rate.getMaxPersons());
            preparedStatement.setInt(2, rate.getPrice());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                rate = new Rate(
                        resultSet.getInt("rate_id"),
                        rate.getMaxPersons(),
                        rate.getPrice()
                );
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return rate;
    }

    @Override
    public void update(Rate rate) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update_SQL);) {
            preparedStatement.setInt(1, rate.getMaxPersons());
            preparedStatement.setInt(2, rate.getPrice());
            preparedStatement.setInt(3, rate.getId());
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
    public Optional<Rate> findById(Integer id) {
        Rate rate = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findById_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rate = createRate(resultSet);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return Optional.ofNullable(rate);
    }

    @Override
    public List<Rate> findAll() {
        List<Rate> rateList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAll_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rateList.add(createRate(resultSet));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return rateList;
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
