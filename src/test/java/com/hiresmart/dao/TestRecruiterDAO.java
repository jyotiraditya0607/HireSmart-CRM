package com.hiresmart;

import com.hiresmart.dao.RecruiterDAO;
import com.hiresmart.model.Recruiter;
import java.util.List;
import java.util.Optional;

public class TestRecruiterDAO {
    public static void main(String[] args) {
        RecruiterDAO recruiterDAO = new RecruiterDAO();
        
        // Test creating a recruiter
        System.out.println("\n=== Testing Create Recruiter ===");
        Recruiter newRecruiter = new Recruiter(
            "REC004",
            "Emily Chen",
            "emily.chen@hiresmart.com",
            "+1-555-0104",
            "Marketing,Digital",
            true,
            10
        );
        Recruiter createdRecruiter = recruiterDAO.create(newRecruiter);
        System.out.println("Created Recruiter: " + createdRecruiter);
        
        // Test finding a recruiter
        System.out.println("\n=== Testing Find Recruiter ===");
        Optional<Recruiter> foundRecruiter = recruiterDAO.findById("REC004");
        foundRecruiter.ifPresent(recruiter -> 
            System.out.println("Found Recruiter: " + recruiter)
        );
        
        // Test updating a recruiter
        System.out.println("\n=== Testing Update Recruiter ===");
        if (foundRecruiter.isPresent()) {
            Recruiter recruiterToUpdate = foundRecruiter.get();
            recruiterToUpdate.setHireCount(11);
            recruiterToUpdate.setExpertise("Marketing,Digital,Social Media");
            Recruiter updatedRecruiter = recruiterDAO.update(recruiterToUpdate);
            System.out.println("Updated Recruiter: " + updatedRecruiter);
        }
        
        // Test finding all recruiters
        System.out.println("\n=== Testing Find All Recruiters ===");
        List<Recruiter> allRecruiters = recruiterDAO.findAll();
        System.out.println("Total Recruiters: " + allRecruiters.size());
        allRecruiters.forEach(recruiter -> 
            System.out.println("- " + recruiter.getName() + " (" + recruiter.getExpertise() + ")")
        );
        
        // Test deleting a recruiter
        System.out.println("\n=== Testing Delete Recruiter ===");
        boolean deleted = recruiterDAO.delete("REC004");
        System.out.println("Recruiter deleted: " + deleted);
        
        // Verify deletion
        Optional<Recruiter> deletedRecruiter = recruiterDAO.findById("REC004");
        System.out.println("Recruiter still exists: " + deletedRecruiter.isPresent());
    }
} 