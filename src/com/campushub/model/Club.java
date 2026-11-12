package com.campushub.model;

import java.time.LocalDate;

public class Club {

    private int clubId;
    private String clubName;
    private String description;
    private String category;
    private LocalDate createdDate;

    public Club() {
    }

    public Club(int clubId, String clubName, String description,
                String category, LocalDate createdDate) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.description = description;
        this.category = category;
        this.createdDate = createdDate;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubId=" + clubId +
                ", clubName='" + clubName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}