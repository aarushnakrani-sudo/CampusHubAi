package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Attendance;
import com.campushub.model.Student;
import com.campushub.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAttendanceManagementDAO {

    private Connection connection;

    public AdminAttendanceManagementDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Attendance> getAllAttendance() {

        List<Attendance> attendanceList = new ArrayList<>();

        String sql =
                "SELECT a.*, s.full_name, sub.subject_name " +
                        "FROM Attendance a " +
                        "JOIN Student s ON a.student_id = s.student_id " +
                        "JOIN Subject sub ON a.subject_id = sub.subject_id " +
                        "ORDER BY s.student_id, sub.subject_id";

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

                Attendance attendance = new Attendance();

                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setStudent(student);
                attendance.setSubject(subject);
                attendance.setTotalClasses(rs.getInt("total_classes"));
                attendance.setPresentClasses(rs.getInt("present_classes"));

                attendanceList.add(attendance);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    public boolean updateAttendance(int studentId,
                                    int subjectId,
                                    int totalClasses,
                                    int presentClasses) {

        String sql =
                "UPDATE Attendance " +
                        "SET total_classes = ?, present_classes = ? " +
                        "WHERE student_id = ? AND subject_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, totalClasses);
            ps.setInt(2, presentClasses);
            ps.setInt(3, studentId);
            ps.setInt(4, subjectId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<String[]> getAttendanceShortageReport() {

        List<String[]> report = new ArrayList<>();

        String sql = "CALL GetAttendanceShortageReport()";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String[] row = new String[4];
                row[0] = String.valueOf(rs.getInt("student_id"));
                row[1] = rs.getString("enrollment_no");
                row[2] = rs.getString("full_name");
                row[3] = String.format("%.2f%%", rs.getDouble("attendance_percentage"));

                report.add(row);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }

}