package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.LostFoundService;

public class LostFoundTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getStudentById(1);

        LostFoundService service = new LostFoundService();
        service.showLostFound(student);

    }

}