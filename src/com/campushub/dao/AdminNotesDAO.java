package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.NotesSubmission;
import com.campushub.model.NotesRequest;
import com.campushub.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminNotesDAO {

    private Connection connection;

    public AdminNotesDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<NotesSubmission> getAllSubmissions() {

        List<NotesSubmission> list = new ArrayList<>();

        String sql =
                "SELECT ns.*, s.full_name, nr.topic " +
                        "FROM NotesSubmission ns " +
                        "JOIN Student s ON ns.student_id = s.student_id " +
                        "JOIN NotesRequest nr ON ns.request_id = nr.request_id " +
                        "ORDER BY ns.submission_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));

                NotesRequest request = new NotesRequest();
                request.setRequestId(rs.getInt("request_id"));
                request.setTopic(rs.getString("topic"));

                NotesSubmission submission = new NotesSubmission();

                submission.setSubmissionId(rs.getInt("submission_id"));
                submission.setStudent(student);
                submission.setRequest(request);
                submission.setFileName(rs.getString("file_name"));
                submission.setFilePath(rs.getString("file_path"));
                submission.setApprovalStatus(rs.getString("approval_status"));

                list.add(submission);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateStatus(int submissionId, String status, int adminId) {

        String sql =
                "UPDATE NotesSubmission SET approval_status=?, admin_id=? WHERE submission_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, adminId);
            ps.setInt(3, submissionId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}