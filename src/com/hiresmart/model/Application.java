package com.hiresmart.model;

import java.sql.Date;

/**
 * Application model class, mapping a candidate to a job
 */
public class Application implements Entity {
    private int applicationId;
    private int candidateId;
    private int jobId;
    private Date applicationDate;
    private String status;

    // Default constructor
    public Application() {}

    // Constructor with all fields
    public Application(int applicationId, int candidateId, int jobId, Date applicationDate, String status) {
        this.applicationId = applicationId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    // Constructor without ID and date (for new application creation)
    public Application(int candidateId, int jobId, String status) {
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.status = status;
    }

    // Getters and Setters
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", candidateId=" + candidateId +
                ", jobId=" + jobId +
                ", applicationDate=" + applicationDate +
                ", status='" + status + '\'' +
                '}';
    }
}