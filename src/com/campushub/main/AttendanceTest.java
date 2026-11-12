package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.AttendanceService;

public class AttendanceTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        // Student ID 1 (Krisha Mor)
        Student student = studentDAO.getStudentById(1);

        if (student != null) {

            AttendanceService attendanceService = new AttendanceService();
            attendanceService.showAttendance(student);

        } else {

            System.out.println("Student not found.");

        }

    }

}