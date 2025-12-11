package com.example.elibraryadmin.controller;

import com.example.elibraryadmin.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

            String sql = "SELECT * FROM ADMIN WHERE adminUSER=? AND adminPassword=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usernameField.getText().trim());
            stmt.setString(2, passwordField.getText().trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                statusLabel.setText("Login successful");

                // Load dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/elibraryadmin/view/AdminDashboardView.fxml"));
                Parent root = loader.load();

                // Pass username to dashboard
                AdminDashboardController dashboardController = loader.getController();
                dashboardController.setWelcomeMessage(rs.getString("adminUSER"));

                Stage stage = new Stage();
                stage.setTitle("Admin Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                // Close login window
                ((Stage) usernameField.getScene().getWindow()).close();

            } else {
                statusLabel.setText("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error");
        }
    }
}
