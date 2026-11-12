package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private Connection connection;

    public AdminDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Admin Login
    public Admin loginAdmin(String email, String password) {

        if (connection == null) {
            System.err.println("\n[ERROR] Database connection is offline. Cannot authenticate admin.");
            return null;
        }

        String sql = "SELECT * FROM Admin WHERE email = ? AND password_hash = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}