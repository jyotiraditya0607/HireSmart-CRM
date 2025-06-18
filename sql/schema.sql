-- HireSmart CRM Database Schema for Oracle
-- Drop tables if they exist (in reverse order due to foreign keys)
DROP TABLE interview CASCADE CONSTRAINTS;
DROP TABLE application CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE candidate CASCADE CONSTRAINTS;
DROP TABLE recruiter CASCADE CONSTRAINTS;

-- Create RECRUITER table
CREATE TABLE recruiter (
    recruiter_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    expertise VARCHAR2(200),
    phone VARCHAR2(20),
    active_status VARCHAR2(10) DEFAULT 'ACTIVE' CHECK (active_status IN ('ACTIVE', 'INACTIVE'))
);

-- Create CANDIDATE table
CREATE TABLE candidate (
    candidate_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    phone VARCHAR2(20),
    skills VARCHAR2(500),
    experience_years NUMBER DEFAULT 0,
    resume_file_path VARCHAR2(500),
    status VARCHAR2(20) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'HIRED')),
    created_date DATE DEFAULT SYSDATE
);

-- Create JOB table
CREATE TABLE job (
    job_id NUMBER PRIMARY KEY,
    recruiter_id NUMBER NOT NULL,
    title VARCHAR2(200) NOT NULL,
    description CLOB,
    requirements VARCHAR2(1000),
    location VARCHAR2(100),
    salary_min NUMBER,
    salary_max NUMBER,
    feedback VARCHAR2(1000),
    posted_date DATE DEFAULT SYSDATE,
    FOREIGN KEY (recruiter_id) REFERENCES recruiter(recruiter_id)
);

-- Create APPLICATION table
CREATE TABLE application (
    application_id NUMBER PRIMARY KEY,
    candidate_id NUMBER NOT NULL,
    job_id NUMBER NOT NULL,
    application_date DATE DEFAULT SYSDATE,
    status VARCHAR2(20) DEFAULT 'APPLIED' CHECK (status IN ('APPLIED', 'SHORTLISTED', 'REJECTED', 'HIRED')),
    FOREIGN KEY (candidate_id) REFERENCES candidate(candidate_id),
    FOREIGN KEY (job_id) REFERENCES job(job_id),
    UNIQUE(candidate_id, job_id)
);

-- Create INTERVIEW table
CREATE TABLE interview (
    interview_id NUMBER PRIMARY KEY,
    candidate_id NUMBER NOT NULL,
    job_id NUMBER NOT NULL,
    recruiter_id NUMBER NOT NULL,
    interview_date DATE,
    interview_time VARCHAR2(20),
    status VARCHAR2(20) DEFAULT 'SCHEDULED' CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED', 'RESCHEDULED')),
    feedback VARCHAR2(1000),
    rating NUMBER CHECK (rating BETWEEN 1 AND 10),
    FOREIGN KEY (candidate_id) REFERENCES candidate(candidate_id),
    FOREIGN KEY (job_id) REFERENCES job(job_id),
    FOREIGN KEY (recruiter_id) REFERENCES recruiter(recruiter_id)
);

-- Create sequences for auto-increment
CREATE SEQUENCE recruiter_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE candidate_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE job_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE application_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE interview_seq START WITH 1 INCREMENT BY 1;

-- Insert sample data
-- Sample Recruiters
INSERT INTO recruiter (recruiter_id, name, email, expertise, phone, active_status) VALUES
(recruiter_seq.NEXTVAL, 'John Smith', 'john.smith@hiresmart.com', 'IT Recruitment', '9876543210', 'ACTIVE');

INSERT INTO recruiter (recruiter_id, name, email, expertise, phone, active_status) VALUES
(recruiter_seq.NEXTVAL, 'Sarah Johnson', 'sarah.johnson@hiresmart.com', 'Finance Recruitment', '9876543211', 'ACTIVE');

-- Sample Candidates
INSERT INTO candidate (candidate_id, name, email, phone, skills, experience_years, status) VALUES
(candidate_seq.NEXTVAL, 'Alice Wilson', 'alice.wilson@email.com', '9123456789', 'Java, Spring, Oracle', 3, 'ACTIVE');

INSERT INTO candidate (candidate_id, name, email, phone, skills, experience_years, status) VALUES
(candidate_seq.NEXTVAL, 'Bob Brown', 'bob.brown@email.com', '9123456788', 'Python, Django, MySQL', 2, 'ACTIVE');

-- Sample Jobs
INSERT INTO job (job_id, recruiter_id, title, description, requirements, location, salary_min, salary_max, posted_date) VALUES
(job_seq.NEXTVAL, 1, 'Java Developer', 'Develop enterprise applications using Java', 'Java, Spring Boot, Oracle DB', 'Bangalore', 600000, 900000, SYSDATE);

INSERT INTO job (job_id, recruiter_id, title, description, requirements, location, salary_min, salary_max, posted_date) VALUES
(job_seq.NEXTVAL, 2, 'Python Developer', 'Build web applications using Python', 'Python, Django, PostgreSQL', 'Chennai', 500000, 800000, SYSDATE);

-- Sample Applications
INSERT INTO application (application_id, candidate_id, job_id, status) VALUES
(application_seq.NEXTVAL, 1, 1, 'APPLIED');

INSERT INTO application (application_id, candidate_id, job_id, status) VALUES
(application_seq.NEXTVAL, 2, 2, 'SHORTLISTED');

-- Sample Interviews
INSERT INTO interview (interview_id, candidate_id, job_id, recruiter_id, interview_date, interview_time, status) VALUES
(interview_seq.NEXTVAL, 1, 1, 1, SYSDATE + 2, '10:00 AM', 'SCHEDULED');

COMMIT;