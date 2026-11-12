package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.datastructure.StudentHashMap;
import com.campushub.model.Department;
import com.campushub.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection connection;

    // HashMap (Data Structure)
    private StudentHashMap studentHashMap = new StudentHashMap();

    public StudentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // =========================
    // Student Login
    // =========================
    public Student loginStudent(String email, String password) {

        if (connection == null) {
            System.err.println("\n[ERROR] Database connection is offline. Cannot authenticate user.");
            return null;
        }

        String sql = "SELECT s.*, d.department_name, d.department_code " +
                "FROM Student s " +
                "JOIN Department d ON s.department_id = d.department_id " +
                "WHERE s.college_email = ? AND s.password_hash = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

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

                studentHashMap.put(student);

                return student;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // Get Student By ID
    // =========================
    public Student getStudentById(int studentId) {

        // Search in HashMap first
        if (studentHashMap.contains(studentId)) {

            return studentHashMap.get(studentId);

        }

        if (connection == null) {
            return null;
        }

        String sql = "SELECT s.*, d.department_name, d.department_code " +
                "FROM Student s " +
                "JOIN Department d ON s.department_id = d.department_id " +
                "WHERE s.student_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

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

                studentHashMap.put(student);

                return student;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // Get All Students
    // =========================
    public List<Student> getAllStudents() {

        List<Student> studentList = new ArrayList<>();

        studentHashMap.clear();

        if (connection == null) {
            return studentList;
        }

        String sql = "SELECT s.*, d.department_name, d.department_code " +
                "FROM Student s " +
                "JOIN Department d ON s.department_id = d.department_id";

        try {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

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

                studentList.add(student);

                // Store in HashMap
                studentHashMap.put(student);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    // =========================
    // Search Student using HashMap
    // =========================

    public Student getStudentFromHashMap(int studentId) {

        return studentHashMap.get(studentId);

    }

}