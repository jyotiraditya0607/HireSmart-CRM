package com.hiresmart.dao;

import com.hiresmart.model.Interview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Interview entity
 * Provides CRUD operations for interview management
 */
public class InterviewDAO {

    private Connection connection;

    public InterviewDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Create a new interview
     * @param interview Interview object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean createInterview(Interview interview) {
        String sql = "INSERT INTO interview (interview_id, candidate_id, job_id, recruiter_id, " +
                "interview_date, interview_time, status, feedback, rating) " +
                "VALUES (interview_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interview.getCandidateId());
            pstmt.setInt(2, interview.getJobId());
            pstmt.setInt(3, interview.getRecruiterId());
            pstmt.setDate(4, interview.getInterviewDate());
            pstmt.setString(5, interview.getInterviewTime());
            pstmt.setString(6, interview.getStatus());
            pstmt.setString(7, interview.getFeedback());
            pstmt.setInt(8, interview.getRating());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Interview created successfully for candidate ID: " + interview.getCandidateId());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating interview: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read interview by ID
     * @param interviewId ID of the interview
     * @return Interview object if found, null otherwise
     */
    public Interview getInterviewById(int interviewId) {
        String sql = "SELECT * FROM interview WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interviewId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createInterviewFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interview by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read all interviews
     * @return List of all interviews
     */
    public List<Interview> getAllInterviews() {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview ORDER BY interview_date DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all interviews: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Update interview information
     * @param interview Interview object with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateInterview(Interview interview) {
        String sql = "UPDATE interview SET candidate_id = ?, job_id = ?, recruiter_id = ?, " +
                "interview_date = ?, interview_time = ?, status = ?, feedback = ?, rating = ? " +
                "WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interview.getCandidateId());
            pstmt.setInt(2, interview.getJobId());
            pstmt.setInt(3, interview.getRecruiterId());
            pstmt.setDate(4, interview.getInterviewDate());
            pstmt.setString(5, interview.getInterviewTime());
            pstmt.setString(6, interview.getStatus());
            pstmt.setString(7, interview.getFeedback());
            pstmt.setInt(8, interview.getRating());
            pstmt.setInt(9, interview.getInterviewId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Interview updated successfully with ID: " + interview.getInterviewId());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating interview: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete interview by ID
     * @param interviewId ID of the interview to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteInterview(int interviewId) {
        String sql = "DELETE FROM interview WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interviewId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Interview deleted successfully with ID: " + interviewId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting interview: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get interviews by candidate ID
     * @param candidateId ID of the candidate
     * @return List of interviews for the candidate
     */
    public List<Interview> getInterviewsByCandidate(int candidateId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE candidate_id = ? ORDER BY interview_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, candidateId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interviews by candidate: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Get interviews by job ID
     * @param jobId ID of the job
     * @return List of interviews for the job
     */
    public List<Interview> getInterviewsByJob(int jobId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE job_id = ? ORDER BY interview_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interviews by job: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Get interviews by recruiter ID
     * @param recruiterId ID of the recruiter
     * @return List of interviews conducted by the recruiter
     */
    public List<Interview> getInterviewsByRecruiter(int recruiterId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE recruiter_id = ? ORDER BY interview_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recruiterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interviews by recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Get interviews by status
     * @param status Status of the interview
     * @return List of interviews with specified status
     */
    public List<Interview> getInterviewsByStatus(String status) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE UPPER(status) = UPPER(?) ORDER BY interview_date DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interviews by status: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Get upcoming interviews (scheduled for future dates)
     * @return List of upcoming interviews
     */
    public List<Interview> getUpcomingInterviews() {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE interview_date >= SYSDATE AND status = 'Scheduled' " +
                "ORDER BY interview_date ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching upcoming interviews: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Get completed interviews with ratings
     * @return List of completed interviews that have ratings
     */
    public List<Interview> getCompletedInterviewsWithRatings() {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interview WHERE status = 'Completed' AND rating IS NOT NULL " +
                "ORDER BY interview_date DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                interviews.add(createInterviewFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching completed interviews with ratings: " + e.getMessage());
            e.printStackTrace();
        }
        return interviews;
    }

    /**
     * Update interview status
     * @param interviewId ID of the interview
     * @param status New status
     * @return true if update successful, false otherwise
     */
    public boolean updateInterviewStatus(int interviewId, String status) {
        String sql = "UPDATE interview SET status = ? WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, interviewId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Interview status updated successfully for ID: " + interviewId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating interview status: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Add feedback and rating to interview
     * @param interviewId ID of the interview
     * @param feedback Feedback text
     * @param rating Rating (1-5)
     * @return true if update successful, false otherwise
     */
    public boolean addInterviewFeedback(int interviewId, String feedback, int rating) {
        String sql = "UPDATE interview SET feedback = ?, rating = ?, status = 'Completed' WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, feedback);
            pstmt.setInt(2, rating);
            pstmt.setInt(3, interviewId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Interview feedback added successfully for ID: " + interviewId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding interview feedback: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get interview statistics by recruiter
     * @param recruiterId ID of the recruiter
     * @return Array containing [total, scheduled, completed, cancelled]
     */
    public int[] getInterviewStatsByRecruiter(int recruiterId) {
        int[] stats = new int[4]; // [total, scheduled, completed, cancelled]
        String sql = "SELECT status, COUNT(*) as count FROM interview WHERE recruiter_id = ? GROUP BY status";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recruiterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");
                stats[0] += count; // total

                switch (status.toLowerCase()) {
                    case "scheduled":
                        stats[1] = count;
                        break;
                    case "completed":
                        stats[2] = count;
                        break;
                    case "cancelled":
                        stats[3] = count;
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching interview statistics: " + e.getMessage());
            e.printStackTrace();
        }
        return stats;
    }

    /**
     * Helper method to create Interview object from ResultSet
     * @param rs ResultSet from database query
     * @return Interview object
     * @throws SQLException if database error occurs
     */
    private Interview createInterviewFromResultSet(ResultSet rs) throws SQLException {
        Interview interview = new Interview();
        interview.setInterviewId(rs.getInt("interview_id"));
        interview.setCandidateId(rs.getInt("candidate_id"));
        interview.setJobId(rs.getInt("job_id"));
        interview.setRecruiterId(rs.getInt("recruiter_id"));
        interview.setInterviewDate(rs.getDate("interview_date"));
        interview.setInterviewTime(rs.getString("interview_time"));
        interview.setStatus(rs.getString("status"));
        interview.setFeedback(rs.getString("feedback"));
        interview.setRating(rs.getInt("rating"));
        return interview;
    }
}