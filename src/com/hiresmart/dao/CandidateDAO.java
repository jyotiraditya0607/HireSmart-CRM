package com.hiresmart.dao;

import com.hiresmart.model.Candidate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Candidate entity
 * Provides CRUD operations for candidate management
 */
public class CandidateDAO {

    private Connection connection;

    public CandidateDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Create a new candidate
     * @param candidate Candidate object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean createCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidate (candidate_id, name, email, phone, skills, experience_years, resume_file_path, status) " +
                "VALUES (candidate_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getEmail());
            pstmt.setString(3, candidate.getPhone());
            pstmt.setString(4, candidate.getSkills());
            pstmt.setInt(5, candidate.getExperienceYears());
            pstmt.setString(6, candidate.getResumeFilePath());
            pstmt.setString(7, candidate.getStatus());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate created successfully: " + candidate.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating candidate: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read candidate by ID
     * @param candidateId ID of the candidate
     * @return Candidate object if found, null otherwise
     */
    public Candidate getCandidateById(int candidateId) {
        String sql = "SELECT * FROM candidate WHERE candidate_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, candidateId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createCandidateFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching candidate by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read all candidates
     * @return List of all candidates
     */
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate ORDER BY candidate_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                candidates.add(createCandidateFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all candidates: " + e.getMessage());
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * Update candidate information
     * @param candidate Candidate object with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateCandidate(Candidate candidate) {
        String sql = "UPDATE candidate SET name = ?, email = ?, phone = ?, skills = ?, " +
                "experience_years = ?, resume_file_path = ?, status = ? WHERE candidate_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getEmail());
            pstmt.setString(3, candidate.getPhone());
            pstmt.setString(4, candidate.getSkills());
            pstmt.setInt(5, candidate.getExperienceYears());
            pstmt.setString(6, candidate.getResumeFilePath());
            pstmt.setString(7, candidate.getStatus());
            pstmt.setInt(8, candidate.getCandidateId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate updated successfully: " + candidate.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating candidate: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete candidate by ID
     * @param candidateId ID of the candidate to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteCandidate(int candidateId) {
        String sql = "DELETE FROM candidate WHERE candidate_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, candidateId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate deleted successfully with ID: " + candidateId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting candidate: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Search candidates by skills
     * @param skills Skills to search for
     * @return List of candidates with matching skills
     */
    public List<Candidate> searchCandidatesBySkills(String skills) {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate WHERE UPPER(skills) LIKE UPPER(?) AND status = 'ACTIVE'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + skills + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                candidates.add(createCandidateFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching candidates by skills: " + e.getMessage());
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * Get candidates by experience range
     * @param minExperience Minimum years of experience
     * @param maxExperience Maximum years of experience
     * @return List of candidates within experience range
     */
    public List<Candidate> getCandidatesByExperience(int minExperience, int maxExperience) {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate WHERE experience_years BETWEEN ? AND ? AND status = 'ACTIVE'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, minExperience);
            pstmt.setInt(2, maxExperience);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                candidates.add(createCandidateFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching candidates by experience: " + e.getMessage());
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * Helper method to create Candidate object from ResultSet
     * @param rs ResultSet from database query
     * @return Candidate object
     * @throws SQLException if database error occurs
     */
    private Candidate createCandidateFromResultSet(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(rs.getInt("candidate_id"));
        candidate.setName(rs.getString("name"));
        candidate.setEmail(rs.getString("email"));
        candidate.setPhone(rs.getString("phone"));
        candidate.setSkills(rs.getString("skills"));
        candidate.setExperienceYears(rs.getInt("experience_years"));
        candidate.setResumeFilePath(rs.getString("resume_file_path"));
        candidate.setStatus(rs.getString("status"));
        candidate.setCreatedDate(rs.getDate("created_date"));
        return candidate;
    }
}