package com.campushub.dao;

import com.campushub.database.DatabaseConnection;
import com.campushub.model.Faculty;
import com.campushub.model.Subject;
import com.campushub.model.Timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {

    private Connection connection;

    public TimetableDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Timetable> getTimetableBySemester(int semester, int departmentId) {

        List<Timetable> timetableList = new ArrayList<>();

        if (connection == null) {
            return timetableList;
        }

        String sql =
                "SELECT t.*, s.subject_name, f.faculty_name " +
                        "FROM Timetable t " +
                        "JOIN Subject s ON t.subject_id = s.subject_id " +
                        "JOIN Faculty f ON t.faculty_id = f.faculty_id " +
                        "WHERE t.semester = ? AND s.department_id = ? " +
                        "ORDER BY FIELD(day,'Monday','Tuesday','Wednesday','Thursday','Friday'), lecture_no";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, semester);
            ps.setInt(2, departmentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                Faculty faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("faculty_id"));
                faculty.setFacultyName(rs.getString("faculty_name"));

                Timetable timetable = new Timetable(
                        rs.getInt("timetable_id"),
                        subject,
                        faculty,
                        null,
                        rs.getString("day"),
                        rs.getInt("lecture_no"),
                        rs.getString("time_slot"),
                        rs.getString("room_no"),
                        rs.getInt("semester")
                );

                timetableList.add(timetable);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timetableList;

    }

}