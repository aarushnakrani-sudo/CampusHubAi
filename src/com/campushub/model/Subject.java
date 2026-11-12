package com.campushub.model;

public class Subject {

    private int subjectId;
    private Department department;
    private String subjectName;
    private int semester;

    // Default Constructor
    public Subject() {
    }

    // Parameterized Constructor
    public Subject(int subjectId, Department department, String subjectName, int semester) {
        this.subjectId = subjectId;
        this.department = department;
        this.subjectName = subjectName;
        this.semester = semester;
    }

    // Getters and Setters

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", department=" + department +
                ", subjectName='" + subjectName + '\'' +
                ", semester=" + semester +
                '}';
    }
}