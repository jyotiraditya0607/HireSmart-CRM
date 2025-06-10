-- Create database
CREATE DATABASE IF NOT EXISTS hiresmart_db;
USE hiresmart_db;

-- Create Recruiters table
CREATE TABLE IF NOT EXISTS recruiters (
    recruiter_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    expertise VARCHAR(200),
    active_status BOOLEAN DEFAULT TRUE,
    hire_count INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Jobs table
CREATE TABLE IF NOT EXISTS jobs (
    job_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    requirements TEXT,
    location VARCHAR(100),
    salary_min DECIMAL(10,2),
    salary_max DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'Open',
    recruiter_id VARCHAR(50),
    posted_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (recruiter_id) REFERENCES recruiters(recruiter_id)
);

-- Create Candidates table
CREATE TABLE IF NOT EXISTS candidates (
    candidate_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    skills TEXT,
    experience_years INT DEFAULT 0,
    resume_file_path VARCHAR(500),
    status VARCHAR(20) DEFAULT 'New',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Interviews table
CREATE TABLE IF NOT EXISTS interviews (
    interview_id VARCHAR(50) PRIMARY KEY,
    candidate_id VARCHAR(50) NOT NULL,
    job_id VARCHAR(50) NOT NULL,
    recruiter_id VARCHAR(50),
    interview_date_time DATETIME,
    status VARCHAR(20) DEFAULT 'Scheduled',
    feedback TEXT,
    rating DECIMAL(3,1) DEFAULT 0.0,
    interview_type VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id),
    FOREIGN KEY (recruiter_id) REFERENCES recruiters(recruiter_id)
);

-- Create Applications junction table
CREATE TABLE IF NOT EXISTS applications (
    application_id VARCHAR(50) PRIMARY KEY,
    candidate_id VARCHAR(50) NOT NULL,
    job_id VARCHAR(50) NOT NULL,
    application_status VARCHAR(20) DEFAULT 'Applied',
    applied_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id),
    UNIQUE KEY unique_application (candidate_id, job_id)
);

-- Insert sample data for recruiters
INSERT INTO recruiters VALUES 
('REC001', 'John Smith', 'john.smith@hiresmart.com', '+1-555-0101', 'Technology,Software', TRUE, 15, NOW()),
('REC002', 'Sarah Johnson', 'sarah.johnson@hiresmart.com', '+1-555-0102', 'Finance,Banking', TRUE, 12, NOW()),
('REC003', 'Mike Davis', 'mike.davis@hiresmart.com', '+1-555-0103', 'Healthcare,Medical', TRUE, 8, NOW());

-- Insert sample data for jobs
INSERT INTO jobs VALUES 
('JOB001', 'Senior Java Developer', 'Experienced Java developer needed', 'Java, Spring, MySQL, 5+ years exp', 'New York', 80000, 120000, 'Open', 'REC001', CURRENT_DATE),
('JOB002', 'Financial Analyst', 'Analyze financial data and trends', 'Excel, SQL, Finance degree, 3+ years', 'Chicago', 60000, 90000, 'Open', 'REC002', CURRENT_DATE),
('JOB003', 'Registered Nurse', 'Patient care in hospital setting', 'RN license, 2+ years experience', 'Boston', 65000, 85000, 'Open', 'REC003', CURRENT_DATE); 