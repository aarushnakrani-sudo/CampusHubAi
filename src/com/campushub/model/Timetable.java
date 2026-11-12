package com.campushub.model;

public class Timetable {

    private int timetableId;
    private Subject subject;
    private Faculty faculty;
    private Admin admin;
    private String day;
    private int lectureNo;
    private String timeSlot;
    private String roomNo;
    private int semester;

    public Timetable() {
    }

    public Timetable(int timetableId, Subject subject, Faculty faculty,
                     Admin admin, String day, int lectureNo,
                     String timeSlot, String roomNo, int semester) {
        this.timetableId = timetableId;
        this.subject = subject;
        this.faculty = faculty;
        this.admin = admin;
        this.day = day;
        this.lectureNo = lectureNo;
        this.timeSlot = timeSlot;
        this.roomNo = roomNo;
        this.semester = semester;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableId=" + timetableId +
                ", subject=" + subject +
                ", day='" + day + '\'' +
                ", lectureNo=" + lectureNo +
                '}';
    }
}