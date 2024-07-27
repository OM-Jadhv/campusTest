package org.owlmon.controller;

import org.owlmon.model.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDashboardController {
    private static final String DB_URL = "jdbc:sqlite:quiz.db";

    public List<Test> loadTestsFromDatabase() {
        List<Test> tests = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM test")) {

            while (rs.next()) {
                int id = rs.getInt("test_id");
                String name = rs.getString("test_name");
                String timer = rs.getString("timer");
                int durationInSeconds = convertTimerToSeconds(timer);
                tests.add(new Test(id, name, durationInSeconds));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    private int convertTimerToSeconds(String timer) {
        String[] parts = timer.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
}