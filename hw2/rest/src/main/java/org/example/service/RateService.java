package org.example.service;

import org.example.database.ConnectionManager;
import org.example.tables.Rate;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RateService implements RateServiceInt {
    private static ConnectionManager connectionManager;
    static {
        try {
            connectionManager = ConnectionManager.getInstance();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String readAll_SQL = """
            SELECT * from rates;
            """;

    private static String findById_SQL = """
            SELECT * from rates WHERE rate_id = 
            """;

    private static String insert_SQL = """
            INSERT INTO rates (max_persons, has_seeview, has_fridge, has_lift_nearby) VALUES (?, ?, ?, ?);
            """;

    private static String delete_SQL = """
            DELETE from rates WHERE rate_id = 
            """;

    private static String update_SQL = """
            UPDATE rates SET has_fridge=?
            WHERE rate_id=?;
            """;

    @Override
    public List<Rate> readAll() {
        Statement statement = null;
        List<Rate> rates = new ArrayList<>();
        try {
            statement = connectionManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAll_SQL);
            while (resultSet.next()) {
                rates.add(new Rate(resultSet));
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rates;
    }

    @Override
    public Rate findById(int elementId) {
        Statement statement = null;
        ResultSet resultSet;
        try {
            statement = connectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(findById_SQL + elementId + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Rate(resultSet);
    }

    @Override
    public void update(Rate element) {
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(update_SQL);
            preparedStatement.setBoolean(1, element.isHasFridge());
            preparedStatement.setInt(2, element.getId());
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
    public void add(Rate element) {
        PreparedStatement statement = null;
        try {
            statement = connectionManager.getConnection().prepareStatement(insert_SQL);
            statement.setInt(1, element.getMax_persons());
            statement.setBoolean(2, element.isHasSeeview());
            statement.setBoolean(3, element.isHasFridge());
            statement.setBoolean(4, element.isHasLiftNearby());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
