package org.example;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/homework";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0000";

    private static ComboPooledDataSource dataSource;

    private DatabaseManager() {}

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            setupDataSource();
        }
        return dataSource.getConnection();
    }

    private static void setupDataSource() {
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);


        dataSource.setInitialPoolSize(5);  // Initial number of connections
        dataSource.setMinPoolSize(5);      // Minimum number of connections
        dataSource.setMaxPoolSize(20);     // Maximum number of connections
        dataSource.setMaxIdleTime(300);    // Maximum idle time (seconds)
    }

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}