package com.hiresmart.model;

import java.sql.Date;

/**
 * Job model class representing a job posting in the system
 */
public class Job {
    private int jobId;
    private int recruiterId;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private double salaryMin;
    private double salaryMax;
    private String feedback;
    private Date postedDate;

    // Default constructor
    public Job() {}

    // Constructor with all fields
    public Job(int jobId, int recruiterId, String title, String description, String requirements,
               String location, double salaryMin, double salaryMax, String feedback, Date postedDate) {
        this.jobId = jobId;
        this.recruiterId = recruiterId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.feedback = feedback;
        this.postedDate = postedDate;
    }

    // Constructor without ID and date (for new job creation)
    public Job(int recruiterId, String title, String description, String requirements,
               String location, double salaryMin, double salaryMax, String feedback) {
        this.recruiterId = recruiterId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.feedback = feedback;
    }

    // Getters and Setters
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
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

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", recruiterId=" + recruiterId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", requirements='" + requirements + '\'' +
                ", location='" + location + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", feedback='" + feedback + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }
}