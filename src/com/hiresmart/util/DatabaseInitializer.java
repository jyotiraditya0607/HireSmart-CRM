package com.hiresmart.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            // Read and execute the schema.sql file
            executeSqlFile(stmt, "src/com/hiresmart/sql/schema.sql");
            
            // Read and execute the data.sql file
            executeSqlFile(stmt, "src/com/hiresmart/sql/data.sql");
            
            stmt.close();
            System.out.println("Database schema and sample data initialized successfully!");
            
        } catch (Exception e) {
            System.out.println("Error initializing database!");
            e.printStackTrace();
        }
    }
    
    private static void executeSqlFile(Statement stmt, String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder sql = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            // Skip comments and empty lines
            if (line.trim().startsWith("--") || line.trim().isEmpty()) {
                continue;
            }
            
            sql.append(line);
            
            // Execute when we find a semicolon
            if (line.trim().endsWith(";")) {
                stmt.execute(sql.toString());
                sql.setLength(0);
            }
        }
        
        reader.close();
    }
} 