package com.campushub.model;

import java.time.LocalDate;

public class NotesRequest {

    private int requestId;
    private Student student;
    private Subject subject;
    private String topic;
    private String description;
    private String status;
    private LocalDate requestDate;

    public NotesRequest() {
    }

    public NotesRequest(int requestId, Student student,
                        Subject subject, String topic,
                        String description, String status,
                        LocalDate requestDate) {
        this.requestId = requestId;
        this.student = student;
        this.subject = subject;
        this.topic = topic;
        this.description = description;
        this.status = status;
        this.requestDate = requestDate;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "NotesRequest{" +
                "requestId=" + requestId +
                ", topic='" + topic + '\'' +
                '}';
    }
}