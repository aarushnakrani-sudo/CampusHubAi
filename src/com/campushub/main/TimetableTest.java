package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.TimetableService;

public class TimetableTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getStudentById(1);

        if (student != null) {

            TimetableService timetableService = new TimetableService();
            timetableService.showTimetable(student);

        } else {

            System.out.println("Student not found.");

        }

    }

}