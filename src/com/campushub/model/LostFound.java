package com.campushub.model;

import java.time.LocalDate;

public class LostFound {

    private int itemId;
    private Student student;
    private Admin admin;
    private String itemName;
    private String description;
    private String location;
    private String type;
    private String status;
    private LocalDate reportDate;
    private String imagePath;

    public LostFound() {
    }

    public LostFound(int itemId, Student student, Admin admin,
                     String itemName, String description,
                     String location, String type,
                     String status, LocalDate reportDate) {
        this.itemId = itemId;
        this.student = student;
        this.admin = admin;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.type = type;
        this.status = status;
        this.reportDate = reportDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "LostFound{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}