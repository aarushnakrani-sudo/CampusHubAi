package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Complaint;
import com.campushub.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    private Connection connection;

    public ComplaintDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Complaint> getStudentComplaints(int studentId) {

        List<Complaint> complaintList = new ArrayList<>();

        String sql = "SELECT * FROM Complaint WHERE student_id=? ORDER BY complaint_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(studentId);

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

    public boolean addComplaint(int studentId,
                                String title,
                                String description) {

        String sql =
                "INSERT INTO Complaint(student_id,title,description,status,submitted_date) VALUES(?,?,?,'Pending',CURDATE())";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setString(2, title);
            ps.setString(3, description);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}