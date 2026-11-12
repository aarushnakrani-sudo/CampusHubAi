package com.campushub.model;

import java.time.LocalDate;

public class Complaint {

    private int complaintId;
    private Student student;
    private Faculty faculty;
    private Admin admin;
    private String title;
    private String description;
    private String status;
    private LocalDate submittedDate;
    private LocalDate resolvedDate;

    public Complaint() {
    }

    public Complaint(int complaintId, Student student, Faculty faculty,
                     Admin admin, String title, String description,
                     String status, LocalDate submittedDate,
                     LocalDate resolvedDate) {
        this.complaintId = complaintId;
        this.student = student;
        this.faculty = faculty;
        this.admin = admin;
        this.title = title;
        this.description = description;
        this.status = status;
        this.submittedDate = submittedDate;
        this.resolvedDate = resolvedDate;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}