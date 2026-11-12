package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Complaint;
import com.campushub.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminComplaintDAO {

    private Connection connection;

    public AdminComplaintDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Complaint> getAllComplaints() {

        List<Complaint> complaintList = new ArrayList<>();

        String sql =
                "SELECT c.*, s.full_name " +
                        "FROM Complaint c " +
                        "JOIN Student s ON c.student_id = s.student_id " +
                        "ORDER BY c.complaint_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));

                Complaint complaint = new Complaint();

                complaint.setComplaintId(rs.getInt("complaint_id"));
                complaint.setStudent(student);
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));

                complaintList.add(complaint);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaintList;

    }

    public boolean resolveComplaint(int complaintId) {

        String sql =
                "UPDATE Complaint SET status='Resolved' WHERE complaint_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, complaintId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}