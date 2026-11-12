package com.campushub.model;

public class Attendance {

    private int attendanceId;
    private Student student;
    private Subject subject;
    private Admin admin;
    private int totalClasses;
    private int presentClasses;

    public Attendance() {
    }

    public Attendance(int attendanceId, Student student, Subject subject,
                      Admin admin, int totalClasses, int presentClasses) {
        this.attendanceId = attendanceId;
        this.student = student;
        this.subject = subject;
        this.admin = admin;
        this.totalClasses = totalClasses;
        this.presentClasses = presentClasses;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getPresentClasses() {
        return presentClasses;
    }

    public void setPresentClasses(int presentClasses) {
        this.presentClasses = presentClasses;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", student=" + student +
                ", subject=" + subject +
                '}';
    }
}