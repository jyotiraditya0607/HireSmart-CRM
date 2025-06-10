package com.hiresmart.model;

import java.time.LocalDateTime;

public class Recruiter {
    private String recruiterId;
    private String name;
    private String email;
    private String phone;
    private String expertise;
    private boolean activeStatus;
    private int hireCount;
    private LocalDateTime createdDate;

    // Constructor
    public Recruiter() {}

    public Recruiter(String recruiterId, String name, String email, String phone, 
                    String expertise, boolean activeStatus, int hireCount) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.expertise = expertise;
        this.activeStatus = activeStatus;
        this.hireCount = hireCount;
        this.createdDate = LocalDateTime.now();
    }

    // Getters and Setters
    public String getRecruiterId() { return recruiterId; }
    public void setRecruiterId(String recruiterId) { this.recruiterId = recruiterId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public boolean isActiveStatus() { return activeStatus; }
    public void setActiveStatus(boolean activeStatus) { this.activeStatus = activeStatus; }

    public int getHireCount() { return hireCount; }
    public void setHireCount(int hireCount) { this.hireCount = hireCount; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    @Override
    public String toString() {
        return "Recruiter{" +
                "recruiterId='" + recruiterId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", expertise='" + expertise + '\'' +
                ", activeStatus=" + activeStatus +
                ", hireCount=" + hireCount +
                ", createdDate=" + createdDate +
                '}';
    }
} 