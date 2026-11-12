package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.ComplaintService;

public class ComplaintTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getStudentById(1);

        ComplaintService complaintService = new ComplaintService();
        complaintService.showComplaints(student);

    }

}