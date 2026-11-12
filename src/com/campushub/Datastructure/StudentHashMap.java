package com.campushub.datastructure;

import com.campushub.model.Student;

import java.util.Collection;
import java.util.HashMap;

public class StudentHashMap {

    private HashMap<Integer, Student> studentMap;

    public StudentHashMap() {

        studentMap = new HashMap<>();

    }

    // Add Student
    public void put(Student student) {

        studentMap.put(student.getStudentId(), student);

    }

    // Search Student
    public Student get(int studentId) {

        return studentMap.get(studentId);

    }

    // Check Student Exists
    public boolean contains(int studentId) {

        return studentMap.containsKey(studentId);

    }

    // Total Students
    public int size() {

        return studentMap.size();

    }

    // Get All Students
    public Collection<Student> values() {

        return studentMap.values();

    }

    // Clear
    public void clear() {

        studentMap.clear();

    }

}