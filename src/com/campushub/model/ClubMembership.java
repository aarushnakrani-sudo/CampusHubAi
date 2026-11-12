package com.campushub.model;

import java.time.LocalDate;

public class ClubMembership {

    private int membershipId;
    private Student student;
    private Club club;
    private LocalDate joinDate;

    public ClubMembership() {
    }

    public ClubMembership(int membershipId, Student student,
                          Club club, LocalDate joinDate) {
        this.membershipId = membershipId;
        this.student = student;
        this.club = club;
        this.joinDate = joinDate;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "ClubMembership{" +
                "membershipId=" + membershipId +
                ", student=" + student +
                ", club=" + club +
                '}';
    }
}