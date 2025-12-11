package com.example.elibraryadmin.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private Label welcomeLabel;

    // Set a custom welcome message
    public void setWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    @FXML
    private void openBookManagement() {
        loadWindow("/com/example/elibraryadmin/view/BookFormView.fxml", "Book Management");
    }

    @FXML
    private void openStudentManagement() {
        loadWindow("/com/example/elibraryadmin/view/StudentFormView.fxml", "Student Management");
    }

    @FXML
    private void logout() {
        // Close current window and return to login
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }

    private void loadWindow(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
