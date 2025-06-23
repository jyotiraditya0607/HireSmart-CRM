package com.hiresmart;

import com.hiresmart.dao.*;
import com.hiresmart.model.*;
import com.hiresmart.util.DateUtil;

import java.util.Scanner;

public class Main {

    private static final RecruiterDAO recruiterDAO = new RecruiterDAO();
    private static final JobDAO jobDAO = new JobDAO();
    private static final CandidateDAO candidateDAO = new CandidateDAO();
    private static final InterviewDAO interviewDAO = new InterviewDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- HireSmart CRM ---");
            System.out.println("1. Manage Recruiters");
            System.out.println("2. Manage Jobs");
            System.out.println("3. Manage Candidates");
            System.out.println("4. Manage Interviews");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageRecruiters(scanner);
                    break;
                case 2:
                    manageJobs(scanner);
                    break;
                case 3:
                    manageCandidates(scanner);
                    break;
                case 4:
                    manageInterviews(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    DBConnection.closeConnection();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageRecruiters(Scanner scanner) {
        System.out.println("\n--- Manage Recruiters ---");
        System.out.println("1. Add Recruiter");
        System.out.println("2. View All Recruiters");
        System.out.println("3. Update Recruiter");
        System.out.println("4. Delete Recruiter");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addRecruiter(scanner);
                break;
            case 2:
                viewAllRecruiters();
                break;
            case 3:
                updateRecruiter(scanner);
                break;
            case 4:
                deleteRecruiter(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addRecruiter(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Expertise: ");
        String expertise = scanner.nextLine();
        Recruiter recruiter = new Recruiter(name, email, phone, expertise, "ACTIVE");
        if (recruiterDAO.createRecruiter(recruiter)) {
            System.out.println("Recruiter added successfully!");
        } else {
            System.out.println("Failed to add recruiter.");
        }
    }

    private static void viewAllRecruiters() {
        System.out.println("\n--- All Recruiters ---");
        recruiterDAO.getAllRecruiters().forEach(System.out::println);
    }

    private static void updateRecruiter(Scanner scanner) {
        System.out.print("Enter Recruiter ID to update: ");
        int recruiterId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Recruiter recruiter = recruiterDAO.getRecruiterById(recruiterId);
        if (recruiter == null) {
            System.out.println("Recruiter not found.");
            return;
        }
        System.out.print("Enter new Name (current: " + recruiter.getName() + "): ");
        recruiter.setName(scanner.nextLine());
        System.out.print("Enter new Email (current: " + recruiter.getEmail() + "): ");
        recruiter.setEmail(scanner.nextLine());
        System.out.print("Enter new Phone (current: " + recruiter.getPhone() + "): ");
        recruiter.setPhone(scanner.nextLine());
        System.out.print("Enter new Expertise (current: " + recruiter.getExpertise() + "): ");
        recruiter.setExpertise(scanner.nextLine());
        System.out.print("Enter new Status (current: " + recruiter.getActiveStatus() + "): ");
        recruiter.setActiveStatus(scanner.nextLine());
        if (recruiterDAO.updateRecruiter(recruiter)) {
            System.out.println("Recruiter updated successfully!");
        } else {
            System.out.println("Failed to update recruiter.");
        }
    }

    private static void deleteRecruiter(Scanner scanner) {
        System.out.print("Enter Recruiter ID to delete: ");
        int recruiterId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (recruiterDAO.deleteRecruiter(recruiterId)) {
            System.out.println("Recruiter deleted successfully!");
        } else {
            System.out.println("Failed to delete recruiter.");
        }
    }

    private static void manageJobs(Scanner scanner) {
        System.out.println("\n--- Manage Jobs ---");
        System.out.println("1. Add Job");
        System.out.println("2. View All Jobs");
        System.out.println("3. Update Job");
        System.out.println("4. Delete Job");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addJob(scanner);
                break;
            case 2:
                viewAllJobs();
                break;
            case 3:
                updateJob(scanner);
                break;
            case 4:
                deleteJob(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addJob(Scanner scanner) {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Requirements: ");
        String requirements = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Minimum Salary: ");
        double salaryMin = scanner.nextDouble();
        System.out.print("Enter Maximum Salary: ");
        double salaryMax = scanner.nextDouble();
        System.out.print("Enter Recruiter ID: ");
        int recruiterId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Job job = new Job(0, recruiterId, title, description, requirements, location, salaryMin, salaryMax, null, null);
        if (jobDAO.createJob(job)) {
            System.out.println("Job added successfully!");
        } else {
            System.out.println("Failed to add job.");
        }
    }

    private static void viewAllJobs() {
        System.out.println("\n--- All Jobs ---");
        jobDAO.getAllJobs().forEach(System.out::println);
    }

    private static void updateJob(Scanner scanner) {
        System.out.print("Enter Job ID to update: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Job job = jobDAO.getJobById(jobId);
        if (job == null) {
            System.out.println("Job not found.");
            return;
        }
        System.out.print("Enter new Title (current: " + job.getTitle() + "): ");
        job.setTitle(scanner.nextLine());
        System.out.print("Enter new Description (current: " + job.getDescription() + "): ");
        job.setDescription(scanner.nextLine());
        System.out.print("Enter new Requirements (current: " + job.getRequirements() + "): ");
        job.setRequirements(scanner.nextLine());
        System.out.print("Enter new Location (current: " + job.getLocation() + "): ");
        job.setLocation(scanner.nextLine());
        System.out.print("Enter new Minimum Salary (current: " + job.getSalaryMin() + "): ");
        job.setSalaryMin(scanner.nextDouble());
        System.out.print("Enter new Maximum Salary (current: " + job.getSalaryMax() + "): ");
        job.setSalaryMax(scanner.nextDouble());
        System.out.print("Enter new Recruiter ID (current: " + job.getRecruiterId() + "): ");
        job.setRecruiterId(scanner.nextInt());
        scanner.nextLine(); // consume newline
        if (jobDAO.updateJob(job)) {
            System.out.println("Job updated successfully!");
        } else {
            System.out.println("Failed to update job.");
        }
    }

    private static void deleteJob(Scanner scanner) {
        System.out.print("Enter Job ID to delete: ");
        int jobId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (jobDAO.deleteJob(jobId)) {
            System.out.println("Job deleted successfully!");
        } else {
            System.out.println("Failed to delete job.");
        }
    }

    private static void manageCandidates(Scanner scanner) {
        System.out.println("\n--- Manage Candidates ---");
        System.out.println("1. Add Candidate");
        System.out.println("2. View All Candidates");
        System.out.println("3. Update Candidate");
        System.out.println("4. Delete Candidate");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addCandidate(scanner);
                break;
            case 2:
                viewAllCandidates();
                break;
            case 3:
                updateCandidate(scanner);
                break;
            case 4:
                deleteCandidate(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addCandidate(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Skills: ");
        String skills = scanner.nextLine();
        System.out.print("Enter Experience Years: ");
        int experience = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Resume File Path: ");
        String resumePath = scanner.nextLine();
        Candidate candidate = new Candidate(name, email, phone, skills, experience, resumePath, "ACTIVE");
        if (candidateDAO.createCandidate(candidate)) {
            System.out.println("Candidate added successfully!");
        } else {
            System.out.println("Failed to add candidate.");
        }
    }

    private static void viewAllCandidates() {
        System.out.println("\n--- All Candidates ---");
        candidateDAO.getAllCandidates().forEach(System.out::println);
    }

    private static void updateCandidate(Scanner scanner) {
        System.out.print("Enter Candidate ID to update: ");
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Candidate candidate = candidateDAO.getCandidateById(candidateId);
        if (candidate == null) {
            System.out.println("Candidate not found.");
            return;
        }
        System.out.print("Enter new Name (current: " + candidate.getName() + "): ");
        candidate.setName(scanner.nextLine());
        System.out.print("Enter new Email (current: " + candidate.getEmail() + "): ");
        candidate.setEmail(scanner.nextLine());
        System.out.print("Enter new Phone (current: " + candidate.getPhone() + "): ");
        candidate.setPhone(scanner.nextLine());
        System.out.print("Enter new Skills (current: " + candidate.getSkills() + "): ");
        candidate.setSkills(scanner.nextLine());
        System.out.print("Enter new Experience Years (current: " + candidate.getExperienceYears() + "): ");
        candidate.setExperienceYears(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new Resume File Path (current: " + candidate.getResumeFilePath() + "): ");
        candidate.setResumeFilePath(scanner.nextLine());
        System.out.print("Enter new Status (current: " + candidate.getStatus() + "): ");
        candidate.setStatus(scanner.nextLine());
        if (candidateDAO.updateCandidate(candidate)) {
            System.out.println("Candidate updated successfully!");
        } else {
            System.out.println("Failed to update candidate.");
        }
    }

    private static void deleteCandidate(Scanner scanner) {
        System.out.print("Enter Candidate ID to delete: ");
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (candidateDAO.deleteCandidate(candidateId)) {
            System.out.println("Candidate deleted successfully!");
        } else {
            System.out.println("Failed to delete candidate.");
        }
    }

    private static void manageInterviews(Scanner scanner) {
        System.out.println("\n--- Manage Interviews ---");
        System.out.println("1. Schedule Interview");
        System.out.println("2. View All Interviews");
        System.out.println("3. Update Interview");
        System.out.println("4. Delete Interview");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                scheduleInterview(scanner);
                break;
            case 2:
                viewAllInterviews();
                break;
            case 3:
                updateInterview(scanner);
                break;
            case 4:
                deleteInterview(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void scheduleInterview(Scanner scanner) {
        System.out.print("Enter Candidate ID: ");
        int candidateId = scanner.nextInt();
        System.out.print("Enter Job ID: ");
        int jobId = scanner.nextInt();
        System.out.print("Enter Recruiter ID: ");
        int recruiterId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Interview Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter Interview Time: ");
        String timeStr = scanner.nextLine();

        Interview interview = new Interview();
        interview.setCandidateId(candidateId);
        interview.setJobId(jobId);
        interview.setRecruiterId(recruiterId);
        interview.setInterviewDate(DateUtil.stringToSqlDate(dateStr));
        interview.setInterviewTime(timeStr);
        interview.setStatus("SCHEDULED");

        if (interviewDAO.createInterview(interview)) {
            System.out.println("Interview scheduled successfully!");
        } else {
            System.out.println("Failed to schedule interview.");
        }
    }

    private static void viewAllInterviews() {
        System.out.println("\n--- All Interviews ---");
        interviewDAO.getAllInterviews().forEach(System.out::println);
    }

    private static void updateInterview(Scanner scanner) {
        System.out.print("Enter Interview ID to update: ");
        int interviewId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Interview interview = interviewDAO.getInterviewById(interviewId);
        if (interview == null) {
            System.out.println("Interview not found.");
            return;
        }

        System.out.print("Enter new Interview Date (current: " + interview.getInterviewDate() + "): ");
        String dateStr = scanner.nextLine();
        interview.setInterviewDate(DateUtil.stringToSqlDate(dateStr));
        System.out.print("Enter new Interview Time (current: " + interview.getInterviewTime() + "): ");
        interview.setInterviewTime(scanner.nextLine());
        System.out.print("Enter new Status (current: " + interview.getStatus() + "): ");
        interview.setStatus(scanner.nextLine());
        System.out.print("Enter Feedback: ");
        interview.setFeedback(scanner.nextLine());
        System.out.print("Enter Rating (1-10): ");
        interview.setRating(scanner.nextInt());
        scanner.nextLine();

        if (interviewDAO.updateInterview(interview)) {
            System.out.println("Interview updated successfully!");
        } else {
            System.out.println("Failed to update interview.");
        }
    }

    private static void deleteInterview(Scanner scanner) {
        System.out.print("Enter Interview ID to delete: ");
        int interviewId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (interviewDAO.deleteInterview(interviewId)) {
            System.out.println("Interview deleted successfully!");
        } else {
            System.out.println("Failed to delete interview.");
        }
    }
} 