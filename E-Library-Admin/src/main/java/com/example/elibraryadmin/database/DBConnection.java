package com.example.elibraryadmin.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ELIBRARY?useSSL=false&serverTimezone=UTC"; // instance
    private static final String USER = "root"; // user
    private static final String PASSWORD = ""; // pass
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to connect to database.");
            }
        }
        return connection;
    }
}
