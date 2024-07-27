package org.owlmon.controller;

import org.owlmon.model.Test;
import org.owlmon.model.question.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDashboardController {
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

    public Test addTest(String name, String duration) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO test (test_name, timer) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, duration);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating test failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Test(id, name, convertTimerToSeconds(duration));
                } else {
                    throw new SQLException("Creating test failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeTest(int testId) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM test WHERE test_id = ?")) {
            pstmt.setInt(1, testId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addQuestion(Question question) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, question.getQuestionSetId());
            pstmt.setInt(2, question.getQuestionNo());
            pstmt.setString(3, question.getQuestionText());
            pstmt.setString(4, formatOptions(question.getOptions()));
            pstmt.setString(5, question.getCorrectOption());
            pstmt.setString(6, question.getType());
            pstmt.setInt(7, question.getMarks());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int convertTimerToSeconds(String timer) {
        String[] parts = timer.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    private String formatOptions(List<String> options) {
        if (options == null || options.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String option : options) {
            sb.append("{").append(option).append("},");
        }
        return sb.substring(0, sb.length() - 1);
    }
}