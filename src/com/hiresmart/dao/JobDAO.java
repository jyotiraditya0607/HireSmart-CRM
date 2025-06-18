package com.hiresmart.dao;

import com.hiresmart.model.Job;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Job entity
 * Provides CRUD operations for job management
 */
public class JobDAO {

    private Connection connection;

    public JobDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Create a new job
     * @param job Job object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean createJob(Job job) {
        String sql = "INSERT INTO job (job_id, recruiter_id, title, description, requirements, " +
                "location, salary_min, salary_max, feedback) VALUES (job_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, job.getRecruiterId());
            pstmt.setString(2, job.getTitle());
            pstmt.setString(3, job.getDescription());
            pstmt.setString(4, job.getRequirements());
            pstmt.setString(5, job.getLocation());
            pstmt.setDouble(6, job.getSalaryMin());
            pstmt.setDouble(7, job.getSalaryMax());
            pstmt.setString(8, job.getFeedback());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Job created successfully: " + job.getTitle());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating job: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read job by ID
     * @param jobId ID of the job
     * @return Job object if found, null otherwise
     */
    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM job WHERE job_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createJobFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching job by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read all jobs
     * @return List of all jobs
     */
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job ORDER BY posted_date DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                jobs.add(createJobFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all jobs: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Update job information
     * @param job Job object with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateJob(Job job) {
        String sql = "UPDATE job SET recruiter_id = ?, title = ?, description = ?, requirements = ?, " +
                "location = ?, salary_min = ?, salary_max = ?, feedback = ? WHERE job_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, job.getRecruiterId());
            pstmt.setString(2, job.getTitle());
            pstmt.setString(3, job.getDescription());
            pstmt.setString(4, job.getRequirements());
            pstmt.setString(5, job.getLocation());
            pstmt.setDouble(6, job.getSalaryMin());
            pstmt.setDouble(7, job.getSalaryMax());
            pstmt.setString(8, job.getFeedback());
            pstmt.setInt(9, job.getJobId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Job updated successfully: " + job.getTitle());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating job: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete job by ID
     * @param jobId ID of the job to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteJob(int jobId) {
        String sql = "DELETE FROM job WHERE job_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, jobId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Job deleted successfully with ID: " + jobId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting job: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get jobs by recruiter ID
     * @param recruiterId ID of the recruiter
     * @return List of jobs posted by the recruiter
     */
    public List<Job> getJobsByRecruiter(int recruiterId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE recruiter_id = ? ORDER BY posted_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recruiterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                jobs.add(createJobFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching jobs by recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Search jobs by location
     * @param location Location to search for
     * @return List of jobs in the specified location
     */
    public List<Job> searchJobsByLocation(String location) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE UPPER(location) LIKE UPPER(?) ORDER BY posted_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + location + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                jobs.add(createJobFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching jobs by location: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Search jobs by salary range
     * @param minSalary Minimum salary
     * @param maxSalary Maximum salary
     * @return List of jobs within salary range
     */
    public List<Job> searchJobsBySalaryRange(double minSalary, double maxSalary) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE salary_min >= ? AND salary_max <= ? ORDER BY salary_max DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, minSalary);
            pstmt.setDouble(2, maxSalary);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                jobs.add(createJobFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching jobs by salary range: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Search jobs by title or requirements
     * @param keyword Keyword to search for
     * @return List of jobs matching the keyword
     */
    public List<Job> searchJobsByKeyword(String keyword) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM job WHERE UPPER(title) LIKE UPPER(?) OR UPPER(requirements) LIKE UPPER(?) " +
                "ORDER BY posted_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                jobs.add(createJobFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching jobs by keyword: " + e.getMessage());
            e.printStackTrace();
        }
        return jobs;
    }

    /**
     * Helper method to create Job object from ResultSet
     * @param rs ResultSet from database query
     * @return Job object
     * @throws SQLException if database error occurs
     */
    private Job createJobFromResultSet(ResultSet rs) throws SQLException {
        Job job = new Job();
        job.setJobId(rs.getInt("job_id"));
        job.setRecruiterId(rs.getInt("recruiter_id"));
        job.setTitle(rs.getString("title"));
        job.setDescription(rs.getString("description"));
        job.setRequirements(rs.getString("requirements"));
        job.setLocation(rs.getString("location"));
        job.setSalaryMin(rs.getDouble("salary_min"));
        job.setSalaryMax(rs.getDouble("salary_max"));
        job.setFeedback(rs.getString("feedback"));
        job.setPostedDate(rs.getDate("posted_date"));
        return job;
    }
}