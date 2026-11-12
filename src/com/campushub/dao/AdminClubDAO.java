package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Club;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminClubDAO {

    private Connection connection;

    public AdminClubDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Club> getAllClubs() {

        List<Club> clubList = new ArrayList<>();

        String sql = "SELECT * FROM Club ORDER BY club_name";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Club club = new Club();

                club.setClubId(rs.getInt("club_id"));
                club.setClubName(rs.getString("club_name"));
                club.setDescription(rs.getString("description"));

                clubList.add(club);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubList;

    }

    public boolean addClub(String clubName, String description) {

        String sql =
                "INSERT INTO Club(club_name,description) VALUES(?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, clubName);
            ps.setString(2, description);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteClub(int clubId) {

        String sql = "DELETE FROM Club WHERE club_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, clubId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<com.campushub.model.Student> getClubMembers(int clubId) {
        List<com.campushub.model.Student> members = new ArrayList<>();
        String sql = "SELECT s.student_id, s.enrollment_no, s.full_name, s.college_email " +
                     "FROM ClubMembership cm " +
                     "JOIN Student s ON cm.student_id = s.student_id " +
                     "WHERE cm.club_id = ? " +
                     "ORDER BY s.full_name";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clubId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                com.campushub.model.Student student = new com.campushub.model.Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setEnrollmentNo(rs.getString("enrollment_no"));
                student.setFullName(rs.getString("full_name"));
                student.setCollegeEmail(rs.getString("college_email"));
                members.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Database error getting club members: " + e.getMessage());
        }
        return members;
    }

}