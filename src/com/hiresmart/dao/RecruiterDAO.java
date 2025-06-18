package com.hiresmart.dao;

import com.hiresmart.model.Recruiter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Recruiter entity
 * Provides CRUD operations for recruiter management
 */
public class RecruiterDAO {

    private Connection connection;

    public RecruiterDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Create a new recruiter
     * @param recruiter Recruiter object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean createRecruiter(Recruiter recruiter) {
        String sql = "INSERT INTO recruiter (recruiter_id, name, email, expertise, phone, active_status) " +
                "VALUES (recruiter_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recruiter.getName());
            pstmt.setString(2, recruiter.getEmail());
            pstmt.setString(3, recruiter.getExpertise());
            pstmt.setString(4, recruiter.getPhone());
            pstmt.setString(5, recruiter.getActiveStatus());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recruiter created successfully: " + recruiter.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read recruiter by ID
     * @param recruiterId ID of the recruiter
     * @return Recruiter object if found, null otherwise
     */
    public Recruiter getRecruiterById(int recruiterId) {
        String sql = "SELECT * FROM recruiter WHERE recruiter_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recruiterId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createRecruiterFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching recruiter by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read all recruiters
     * @return List of all recruiters
     */
    public List<Recruiter> getAllRecruiters() {
        List<Recruiter> recruiters = new ArrayList<>();
        String sql = "SELECT * FROM recruiter ORDER BY recruiter_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                recruiters.add(createRecruiterFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all recruiters: " + e.getMessage());
            e.printStackTrace();
        }
        return recruiters;
    }

    /**
     * Update recruiter information
     * @param recruiter Recruiter object with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateRecruiter(Recruiter recruiter) {
        String sql = "UPDATE recruiter SET name = ?, email = ?, expertise = ?, phone = ?, " +
                "active_status = ? WHERE recruiter_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recruiter.getName());
            pstmt.setString(2, recruiter.getEmail());
            pstmt.setString(3, recruiter.getExpertise());
            pstmt.setString(4, recruiter.getPhone());
            pstmt.setString(5, recruiter.getActiveStatus());
            pstmt.setInt(6, recruiter.getRecruiterId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recruiter updated successfully: " + recruiter.getName());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete recruiter by ID
     * @param recruiterId ID of the recruiter to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteRecruiter(int recruiterId) {
        String sql = "DELETE FROM recruiter WHERE recruiter_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recruiterId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Recruiter deleted successfully with ID: " + recruiterId);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get active recruiters only
     * @return List of active recruiters
     */
    public List<Recruiter> getActiveRecruiters() {
        List<Recruiter> recruiters = new ArrayList<>();
        String sql = "SELECT * FROM recruiter WHERE active_status = 'ACTIVE' ORDER BY name";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                recruiters.add(createRecruiterFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching active recruiters: " + e.getMessage());
            e.printStackTrace();
        }
        return recruiters;
    }

    /**
     * Search recruiters by expertise
     * @param expertise Expertise to search for
     * @return List of recruiters with matching expertise
     */
    public List<Recruiter> searchRecruitersByExpertise(String expertise) {
        List<Recruiter> recruiters = new ArrayList<>();
        String sql = "SELECT * FROM recruiter WHERE UPPER(expertise) LIKE UPPER(?) AND active_status = 'ACTIVE'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + expertise + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                recruiters.add(createRecruiterFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching recruiters by expertise: " + e.getMessage());
            e.printStackTrace();
        }
        return recruiters;
    }

    /**
     * Get recruiter by email
     * @param email Email of the recruiter
     * @return Recruiter object if found, null otherwise
     */
    public Recruiter getRecruiterByEmail(String email) {
        String sql = "SELECT * FROM recruiter WHERE email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createRecruiterFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching recruiter by email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to create Recruiter object from ResultSet
     * @param rs ResultSet from database query
     * @return Recruiter object
     * @throws SQLException if database error occurs
     */
    private Recruiter createRecruiterFromResultSet(ResultSet rs) throws SQLException {
        Recruiter recruiter = new Recruiter();
        recruiter.setRecruiterId(rs.getInt("recruiter_id"));
        recruiter.setName(rs.getString("name"));
        recruiter.setEmail(rs.getString("email"));
        recruiter.setExpertise(rs.getString("expertise"));
        recruiter.setPhone(rs.getString("phone"));
        recruiter.setActiveStatus(rs.getString("active_status"));
        return recruiter;
    }
}