package com.campushub.model;

import java.time.LocalDateTime;

public class Announcement {

    private int announcementId;
    private Admin admin;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    // Default Constructor
    public Announcement() {
    }

    // Parameterized Constructor
    public Announcement(int announcementId,
                        Admin admin,
                        String title,
                        String description,
                        LocalDateTime createdAt) {

        this.announcementId = announcementId;
        this.admin = admin;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", title='" + title + '\'' +
                ", admin=" + admin.getName() +
                ", createdAt=" + createdAt +
                '}';
    }
}