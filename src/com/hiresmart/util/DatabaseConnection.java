package com.hiresmart.util;

import com.hiresmart.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            // Register JDBC driver
            Class.forName(DatabaseConfig.JDBC_DRIVER);
            
            // Open a connection
            Connection connection = DriverManager.getConnection(
                DatabaseConfig.DB_URL,
                DatabaseConfig.USER,
                DatabaseConfig.PASSWORD
            );
            
            System.out.println("Database connection established successfully!");
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.out.println("Error closing connection!");
                e.printStackTrace();
            }
        }
    }
} 