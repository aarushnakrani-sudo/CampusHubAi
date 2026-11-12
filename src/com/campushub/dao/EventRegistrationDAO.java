package com.campushub.dao;

import com.campushub.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRegistrationDAO {

    private Connection connection;

    public EventRegistrationDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public boolean isAlreadyRegistered(int studentId, int eventId) {

        String sql = "SELECT * FROM EventRegistration WHERE student_id = ? AND event_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, eventId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Database error during checking: " + e.getMessage());
        }

        return false;
    }

    public boolean registerEvent(int studentId, int eventId) {

        String sql = "INSERT INTO EventRegistration(student_id, event_id, registration_date) VALUES (?, ?, CURDATE())";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, eventId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error during registration: " + e.getMessage());
        }

        return false;
    }

}