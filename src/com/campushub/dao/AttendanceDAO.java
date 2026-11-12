package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Admin;
import com.campushub.model.Attendance;
import com.campushub.model.Department;
import com.campushub.model.Student;
import com.campushub.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    private Connection connection;

    public AttendanceDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Attendance> getAttendanceByStudentId(int studentId) {

        List<Attendance> attendanceList = new ArrayList<>();

        String sql =
                "SELECT a.*, s.subject_name, s.semester, " +
                        "st.full_name, st.enrollment_no, st.college_email, st.password_hash, st.phone, " +
                        "d.department_id, d.department_name, d.department_code " +
                        "FROM Attendance a " +
                        "JOIN Subject s ON a.subject_id = s.subject_id " +
                        "JOIN Student st ON a.student_id = st.student_id " +
                        "JOIN Department d ON st.department_id = d.department_id " +
                        "WHERE a.student_id = ? " +
                        "ORDER BY s.subject_id";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Department department = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_code")
                );

                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("enrollment_no"),
                        rs.getString("full_name"),
                        rs.getString("college_email"),
                        rs.getString("password_hash"),
                        department,
                        rs.getInt("semester"),
                        rs.getString("phone")
                );

                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setSemester(rs.getInt("semester"));

                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));

                Attendance attendance = new Attendance(
                        rs.getInt("attendance_id"),
                        student,
                        subject,
                        admin,
                        rs.getInt("total_classes"),
                        rs.getInt("present_classes")
                );

                attendanceList.add(attendance);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;

    }

}