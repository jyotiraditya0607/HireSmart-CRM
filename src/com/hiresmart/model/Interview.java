package com.hiresmart.model;

import java.sql.Date;

/**
 * Interview model class representing an interview in the system
 */
public class Interview {
    private int interviewId;
    private int candidateId;
    private int jobId;
    private int recruiterId;
    private Date interviewDate;
    private String interviewTime;
    private String status;
    private String feedback;
    private int rating;

    // Default constructor
    public Interview() {}

    // Constructor with all fields
    public Interview(int interviewId, int candidateId, int jobId, int recruiterId,
                     Date interviewDate, String interviewTime, String status, String feedback, int rating) {
        this.interviewId = interviewId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.recruiterId = recruiterId;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.status = status;
        this.feedback = feedback;
        this.rating = rating;
    }

    // Constructor without ID (for new interview creation)
    public Interview(int candidateId, int jobId, int recruiterId, Date interviewDate,
                     String interviewTime, String status, String feedback, int rating) {
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.recruiterId = recruiterId;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.status = status;
        this.feedback = feedback;
        this.rating = rating;
    }

    // Getters and Setters
    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
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

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", candidateId=" + candidateId +
                ", jobId=" + jobId +
                ", recruiterId=" + recruiterId +
                ", interviewDate=" + interviewDate +
                ", interviewTime='" + interviewTime + '\'' +
                ", status='" + status + '\'' +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }
}