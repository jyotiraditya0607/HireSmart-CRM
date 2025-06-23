package com.hiresmart.model;

import java.sql.Date;

/**
 * Candidate model class representing a job candidate in the system
 */
public class Candidate extends Person implements Entity {
    private int candidateId;
    private String skills;
    private int experienceYears;
    private String resumeFilePath;
    private String status;
    private Date createdDate;

    // Default constructor
    public Candidate() {
        super();
    }

    // Constructor with all fields
    public Candidate(int candidateId, String name, String email, String phone, String skills,
                     int experienceYears, String resumeFilePath, String status, Date createdDate) {
        super(name, email, phone);
        this.candidateId = candidateId;
        this.skills = skills;
        this.experienceYears = experienceYears;
        this.resumeFilePath = resumeFilePath;
        this.status = status;
        this.createdDate = createdDate;
    }

    // Constructor without ID and date (for new candidate creation)
    public Candidate(String name, String email, String phone, String skills,
                     int experienceYears, String resumeFilePath, String status) {
        super(name, email, phone);
        this.skills = skills;
        this.experienceYears = experienceYears;
        this.resumeFilePath = resumeFilePath;
        this.status = status;
    }

    // Getters and Setters
    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getResumeFilePath() {
        return resumeFilePath;
    }

    public void setResumeFilePath(String resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", skills='" + skills + '\'' +
                ", experienceYears=" + experienceYears +
                ", resumeFilePath='" + resumeFilePath + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}