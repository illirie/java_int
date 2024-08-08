package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManagerImpl implements ConnectionManager {
    private static final String DRIVER_CLASS_KEY = "org.postgresql.Driver";
    private static final String URL_KEY = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USERNAME_KEY = "karin";
    private static final String PASSWORD_KEY = "241002";
    private static ConnectionManager instance;

    private ConnectionManagerImpl() {
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
            try {
                Class.forName(DRIVER_CLASS_KEY);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_KEY, USERNAME_KEY, PASSWORD_KEY);
    }
}