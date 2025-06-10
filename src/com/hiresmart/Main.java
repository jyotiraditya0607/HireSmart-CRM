package com.hiresmart;

import com.hiresmart.util.DatabaseConnection;
import com.hiresmart.util.DatabaseInitializer;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Initialize database schema
        DatabaseInitializer.initializeDatabase();
        
        // Test database connection
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            System.out.println("Successfully connected to the database!");
            // Close the connection when done
            DatabaseConnection.closeConnection(conn);
        }
    }
} 