package com.hiresmart.model;

/**
 * Recruiter model class representing a recruiter in the system
 */
public class Recruiter extends Person implements Entity {
    private int recruiterId;
    private String expertise;
    private String activeStatus;

    // Default constructor
    public Recruiter() {
        super();
    }

    // Constructor with all fields
    public Recruiter(int recruiterId, String name, String email, String phone, String expertise, String activeStatus) {
        super(name, email, phone);
        this.recruiterId = recruiterId;
        this.expertise = expertise;
        this.activeStatus = activeStatus;
    }

    // Constructor without ID (for new recruiter creation)
    public Recruiter(String name, String email, String phone, String expertise, String activeStatus) {
        super(name, email, phone);
        this.expertise = expertise;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
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
                ", phone='" + phone + '\'' +
                ", expertise='" + expertise + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}';
    }
}