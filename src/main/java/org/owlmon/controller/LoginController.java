package org.owlmon.controller;

import org.owlmon.model.user.User;
import java.sql.*;

public class LoginController {
    private static final String DB_URL = "jdbc:sqlite:quiz.db";

    public User authenticateUser(String username, String password, String userType) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ? AND usertype = ?")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, userType);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("usertype"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}