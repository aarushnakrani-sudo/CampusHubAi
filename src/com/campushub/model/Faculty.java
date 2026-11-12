package com.campushub.model;

public class Faculty {

    private int facultyId;
    private Admin admin;
    private Department department;
    private String facultyName;
    private String email;

    // Default Constructor
    public Faculty() {
    }

    // Parameterized Constructor
    public Faculty(int facultyId, Admin admin, Department department,
                   String facultyName, String email) {
        this.facultyId = facultyId;
        this.admin = admin;
        this.department = department;
        this.facultyName = facultyName;
        this.email = email;
    }

    // Getters and Setters

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", admin=" + admin +
                '}';
    }
}