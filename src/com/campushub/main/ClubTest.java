package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.ClubService;

public class ClubTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getStudentById(1);

        ClubService clubService = new ClubService();
        clubService.showClubs(student);

    }

}