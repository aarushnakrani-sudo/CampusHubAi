package com.campushub.model;

import java.time.LocalDate;

public class EventRegistration {

    private int registrationId;
    private Student student;
    private Event event;
    private LocalDate registrationDate;

    public EventRegistration() {
    }

    public EventRegistration(int registrationId, Student student,
                             Event event, LocalDate registrationDate) {
        this.registrationId = registrationId;
        this.student = student;
        this.event = event;
        this.registrationDate = registrationDate;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "EventRegistration{" +
                "registrationId=" + registrationId +
                ", student=" + student +
                ", event=" + event +
                '}';
    }
}