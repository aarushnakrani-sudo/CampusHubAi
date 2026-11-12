package com.campushub.model;

public class Student {


    private int studentId;
    private String enrollmentNo;
    private String fullName;
    private String collegeEmail;
    private String passwordHash;
    private Department department;
    private int semester;
    private String phone;


    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(int studentId, String enrollmentNo, String fullName,
                   String collegeEmail, String passwordHash,
                   Department department, int semester, String phone) {

        this.studentId = studentId;
        this.enrollmentNo = enrollmentNo;
        this.fullName = fullName;
        this.collegeEmail = collegeEmail;
        this.passwordHash = passwordHash;
        this.department = department;
        this.semester = semester;
        this.phone = phone;
    }

    // Getters and Setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCollegeEmail() {
        return collegeEmail;
    }

    public void setCollegeEmail(String collegeEmail) {
        this.collegeEmail = collegeEmail;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", enrollmentNo='" + enrollmentNo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", collegeEmail='" + collegeEmail + '\'' +
                ", department=" + department +
                ", semester=" + semester +
                ", phone='" + phone + '\'' +
                '}';
    }
}