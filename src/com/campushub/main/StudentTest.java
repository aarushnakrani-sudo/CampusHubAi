package com.campushub.main;

import com.campushub.dao.StudentDAO;
import com.campushub.model.Student;

import java.util.List;

public class StudentTest {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        List<Student> students = dao.getAllStudents();

        for (Student student : students) {

            System.out.println(student);

        }

    }

}