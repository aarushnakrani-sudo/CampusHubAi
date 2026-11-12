package com.campushub.dao;

import com.campushub.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubMembershipDAO {

    private Connection connection;

    public ClubMembershipDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public boolean isAlreadyMember(int studentId, int clubId) {

        String sql = "SELECT * FROM ClubMembership WHERE student_id = ? AND club_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, clubId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Database error checking membership: " + e.getMessage());
        }

        return false;
    }

    public boolean joinClub(int studentId, int clubId) {

        String sql = "INSERT INTO ClubMembership(student_id, club_id, join_date) VALUES (?, ?, CURDATE())";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, clubId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error joining club: " + e.getMessage());
        }

        return false;
    }
}