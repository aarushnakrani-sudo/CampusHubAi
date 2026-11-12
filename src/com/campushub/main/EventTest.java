package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.EventService;

public class EventTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.getStudentById(1);

        EventService eventService = new EventService();
        eventService.showEvents(student);

    }

}