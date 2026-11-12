package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Admin;
import com.campushub.model.NotesRequest;
import com.campushub.model.NotesSubmission;
import com.campushub.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesSubmissionDAO {

    private Connection connection;

    public NotesSubmissionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<NotesSubmission> getAllNotes() {

        List<NotesSubmission> notesList = new ArrayList<>();

        String sql =
                "SELECT ns.*, " +
                        "s.full_name, " +
                        "a.name, " +
                        "nr.request_id, " +
                        "nr.topic " +
                        "FROM NotesSubmission ns " +
                        "JOIN Student s ON ns.student_id = s.student_id " +
                        "LEFT JOIN Admin a ON ns.admin_id = a.admin_id " +
                        "JOIN NotesRequest nr ON ns.request_id = nr.request_id " +
                        "ORDER BY ns.submission_id DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));

                Admin admin = new Admin();
                int adminId = rs.getInt("admin_id");
                if (!rs.wasNull()) {
                    admin.setAdminId(adminId);
                    admin.setName(rs.getString("name"));
                } else {
                    admin.setAdminId(0);
                    admin.setName("N/A");
                }

                NotesRequest request = new NotesRequest();
                request.setRequestId(rs.getInt("request_id"));
                request.setTopic(rs.getString("topic"));

                NotesSubmission submission = new NotesSubmission();

                submission.setSubmissionId(rs.getInt("submission_id"));
                submission.setRequest(request);
                submission.setStudent(student);
                submission.setAdmin(admin);
                submission.setFileName(rs.getString("file_name"));
                submission.setFilePath(rs.getString("file_path"));
                submission.setApprovalStatus(rs.getString("approval_status"));
                submission.setUploadDate(rs.getDate("upload_date").toLocalDate());

                notesList.add(submission);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notesList;

    }

}