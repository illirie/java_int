package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManagerInt {
    Connection getConnection() throws SQLException;
}