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

public class NotesRequestDAO {

    private Connection connection;

    public NotesRequestDAO() {
        connection = DatabaseConnection.getConnection();
    }

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

    public boolean createRequest(int studentId, int subjectId, String topic, String description) {
        String sql = "INSERT INTO NotesRequest (student_id, subject_id, topic, description, status, request_date) " +
                     "VALUES (?, ?, ?, ?, 'Pending', CURDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);
            ps.setString(3, topic);
            ps.setString(4, description);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject ORDER BY subject_name";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setSemester(rs.getInt("semester"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

}