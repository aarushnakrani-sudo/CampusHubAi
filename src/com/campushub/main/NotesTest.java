package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;
import com.campushub.service.NotesService;
import java.util.List;

public class NotesTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudents();
        Student student;
        if (students.isEmpty()) {
            student = new Student();
            student.setStudentId(1);
            student.setFullName("Test Student");
            com.campushub.model.Department dept = new com.campushub.model.Department();
            dept.setDepartmentName("Computer Engineering");
            student.setDepartment(dept);
            student.setSemester(2);
        } else {
            student = students.get(0);
        }

        NotesService notesService = new NotesService();
        notesService.showNotes(student);

    }

}