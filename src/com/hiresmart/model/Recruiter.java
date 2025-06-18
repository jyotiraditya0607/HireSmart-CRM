package com.hiresmart.model;

/**
 * Recruiter model class representing a recruiter in the system
 */
public class Recruiter {
    private int recruiterId;
    private String name;
    private String email;
    private String expertise;
    private String phone;
    private String activeStatus;

    // Default constructor
    public Recruiter() {}

    // Constructor with all fields
    public Recruiter(int recruiterId, String name, String email, String expertise, String phone, String activeStatus) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.phone = phone;
        this.activeStatus = activeStatus;
    }

    // Constructor without ID (for new recruiter creation)
    public Recruiter(String name, String email, String expertise, String phone, String activeStatus) {
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.phone = phone;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "recruiterId=" + recruiterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", expertise='" + expertise + '\'' +
                ", phone='" + phone + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}';
    }
}