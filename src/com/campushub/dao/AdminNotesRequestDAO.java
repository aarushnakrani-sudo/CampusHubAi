package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.NotesRequest;
import com.campushub.model.Student;
import com.campushub.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminNotesRequestDAO {

    private Connection connection;

    public AdminNotesRequestDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // ==========================================
    // Get All Notes Requests
    // ==========================================
    public List<NotesRequest> getAllRequests() {

        List<NotesRequest> requestList = new ArrayList<>();

        String sql =
                "SELECT nr.*, s.full_name, sub.subject_name " +
                        "FROM NotesRequest nr " +
                        "JOIN Student s ON nr.student_id = s.student_id " +
                        "JOIN Subject sub ON nr.subject_id = sub.subject_id " +
                        "ORDER BY nr.request_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));

                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                NotesRequest request = new NotesRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setStudent(student);
                request.setSubject(subject);
                request.setTopic(rs.getString("topic"));
                request.setDescription(rs.getString("description"));
                request.setStatus(rs.getString("status"));
                request.setRequestDate(rs.getDate("request_date").toLocalDate());

                requestList.add(request);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return requestList;

    }

    // ==========================================
    // Approve Request
    // ==========================================

    public boolean approveRequest(int requestId) {

        String sql =
                "UPDATE NotesRequest SET status='Approved' WHERE request_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, requestId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================================
    // Reject Request
    // ==========================================

    public boolean rejectRequest(int requestId) {

        String sql =
                "UPDATE NotesRequest SET status='Rejected' WHERE request_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, requestId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

}