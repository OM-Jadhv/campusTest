package org.owlmon.controller;

import org.owlmon.view.LoginView;
import org.owlmon.view.TestDashboard;
import org.owlmon.view.TeacherDashboard;
import org.owlmon.view.TestFrame;
import org.owlmon.model.user.User;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Collectors;

import javax.swing.*;

public class MainController {
    private LoginView loginView;
    private LoginController loginController;
    private static final String DB_URL = "jdbc:sqlite:quiz.db";
    public void init() {
        initializeDatabase();
        loginController = new LoginController();
        showLoginView();
    }

    private void initializeDatabase() {
        try {
            // Load the schema.sql file from resources
            String schema = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/schema.sql")))
                    .lines().collect(Collectors.joining("\n"));

            // Execute the SQL statements
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement()) {
                for (String sql : schema.split(";")) {
                    if (!sql.trim().isEmpty()) {
                        stmt.execute(sql);
                    }
                }
                System.out.println("Database schema initialized successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error initializing database schema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showLoginView() {
        SwingUtilities.invokeLater(() -> {
            loginView = new LoginView(this);
            loginView.setVisible(true);
        });
    }


    public void handleLogin(String username, String password, String userType) {
        User user = loginController.authenticateUser(username, password, userType);
        if (user != null) {
            if ("student".equals(user.getUsertype())) {
                openTestDashboard(user);
            } else if ("teacher".equals(user.getUsertype())) {
                openTeacherDashboard(user);
            }
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "Invalid username, password, or user type", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openTestDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            TestDashboard dashboard = new TestDashboard(user, this);
            dashboard.setVisible(true);
        });
    }

    private void openTeacherDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            TeacherDashboard dashboard = new TeacherDashboard(user, this);
            dashboard.setVisible(true);
        });
    }

    public void openTestFrame(int testId, String testName, int durationInSeconds) {
        SwingUtilities.invokeLater(() -> {
            QuestionController questionController = new QuestionController();
            questionController.loadQuestions(testId);
            TestFrame testFrame = new TestFrame(testName, durationInSeconds, questionController);
            testFrame.setVisible(true);
        });
    }
}