package com.example.elibraryadmin.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.elibraryadmin.database.DBConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    public void login(ActionEvent event) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                statusLabel.setText("DB connection failed");
                return;
            }

            String sql = "SELECT * FROM admins WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usernameField.getText());
            stmt.setString(2, passwordField.getText());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                statusLabel.setText("Login successful");
                // load dashboard here
            } else {
                statusLabel.setText("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error");
        }
    }
}
