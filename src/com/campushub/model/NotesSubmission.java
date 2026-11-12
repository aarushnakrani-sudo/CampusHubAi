package com.campushub.model;

import java.time.LocalDate;

public class NotesSubmission {

    private int submissionId;
    private NotesRequest request;
    private Student student;
    private Admin admin;
    private String fileName;
    private String filePath;
    private String approvalStatus;
    private LocalDate uploadDate;

    public NotesSubmission() {
    }

    public NotesSubmission(int submissionId, NotesRequest request,
                           Student student, Admin admin,
                           String fileName, String filePath,
                           String approvalStatus,
                           LocalDate uploadDate) {
        this.submissionId = submissionId;
        this.request = request;
        this.student = student;
        this.admin = admin;
        this.fileName = fileName;
        this.filePath = filePath;
        this.approvalStatus = approvalStatus;
        this.uploadDate = uploadDate;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public NotesRequest getRequest() {
        return request;
    }

    public void setRequest(NotesRequest request) {
        this.request = request;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "NotesSubmission{" +
                "submissionId=" + submissionId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}