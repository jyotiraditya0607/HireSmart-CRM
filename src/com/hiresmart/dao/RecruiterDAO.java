package com.hiresmart.dao;

import com.hiresmart.model.Recruiter;
import com.hiresmart.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecruiterDAO implements BaseDAO<Recruiter> {
    
    @Override
    public Recruiter create(Recruiter recruiter) {
        String sql = "INSERT INTO recruiters (recruiter_id, name, email, phone, expertise, active_status, hire_count) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) return null;
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, recruiter.getRecruiterId());
            pstmt.setString(2, recruiter.getName());
            pstmt.setString(3, recruiter.getEmail());
            pstmt.setString(4, recruiter.getPhone());
            pstmt.setString(5, recruiter.getExpertise());
            pstmt.setInt(6, recruiter.isActiveStatus() ? 1 : 0);
            pstmt.setInt(7, recruiter.getHireCount());
            
            pstmt.executeUpdate();
            pstmt.close();
            return recruiter;
            
        } catch (SQLException e) {
            System.out.println("Error creating recruiter: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public Optional<Recruiter> findById(String id) {
        String sql = "SELECT * FROM recruiters WHERE recruiter_id = ?";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) return Optional.empty();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Recruiter recruiter = extractRecruiterFromResultSet(rs);
                rs.close();
                pstmt.close();
                return Optional.of(recruiter);
            }
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error finding recruiter: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        
        return Optional.empty();
    }
    
    @Override
    public List<Recruiter> findAll() {
        List<Recruiter> recruiters = new ArrayList<>();
        String sql = "SELECT * FROM recruiters";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) return recruiters;
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                recruiters.add(extractRecruiterFromResultSet(rs));
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error finding all recruiters: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        
        return recruiters;
    }
    
    @Override
    public Recruiter update(Recruiter recruiter) {
        String sql = "UPDATE recruiters SET name = ?, email = ?, phone = ?, " +
                    "expertise = ?, active_status = ?, hire_count = ? WHERE recruiter_id = ?";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) return null;
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, recruiter.getName());
            pstmt.setString(2, recruiter.getEmail());
            pstmt.setString(3, recruiter.getPhone());
            pstmt.setString(4, recruiter.getExpertise());
            pstmt.setInt(5, recruiter.isActiveStatus() ? 1 : 0);
            pstmt.setInt(6, recruiter.getHireCount());
            pstmt.setString(7, recruiter.getRecruiterId());
            
            int affectedRows = pstmt.executeUpdate();
            pstmt.close();
            
            if (affectedRows > 0) {
                return recruiter;
            }
            
        } catch (SQLException e) {
            System.out.println("Error updating recruiter: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        
        return null;
    }
    
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM recruiters WHERE recruiter_id = ?";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) return false;
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            pstmt.close();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.out.println("Error deleting recruiter: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    private Recruiter extractRecruiterFromResultSet(ResultSet rs) throws SQLException {
        Recruiter recruiter = new Recruiter();
        recruiter.setRecruiterId(rs.getString("recruiter_id"));
        recruiter.setName(rs.getString("name"));
        recruiter.setEmail(rs.getString("email"));
        recruiter.setPhone(rs.getString("phone"));
        recruiter.setExpertise(rs.getString("expertise"));
        recruiter.setActiveStatus(rs.getInt("active_status") == 1);
        recruiter.setHireCount(rs.getInt("hire_count"));
        recruiter.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        return recruiter;
    }
}
