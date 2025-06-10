-- Create sequences for IDs
CREATE SEQUENCE recruiter_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE job_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE candidate_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE interview_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE application_seq START WITH 1 INCREMENT BY 1;

-- Create Recruiters table
CREATE TABLE recruiters (
    recruiter_id VARCHAR2(50) PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    phone VARCHAR2(20),
    expertise VARCHAR2(200),
    active_status NUMBER(1) DEFAULT 1,
    hire_count NUMBER DEFAULT 0,
    created_date TIMESTAMP DEFAULT SYSTIMESTAMP
);

-- Create Jobs table
CREATE TABLE jobs (
    job_id VARCHAR2(50) PRIMARY KEY,
    title VARCHAR2(200) NOT NULL,
    description CLOB,
    requirements CLOB,
    location VARCHAR2(100),
    salary_min NUMBER(10,2),
    salary_max NUMBER(10,2),
    status VARCHAR2(20) DEFAULT 'Open',
    recruiter_id VARCHAR2(50),
    posted_date DATE DEFAULT SYSDATE,
    CONSTRAINT fk_job_recruiter FOREIGN KEY (recruiter_id) REFERENCES recruiters(recruiter_id)
);

-- Create Candidates table
CREATE TABLE candidates (
    candidate_id VARCHAR2(50) PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    phone VARCHAR2(20),
    skills CLOB,
    experience_years NUMBER DEFAULT 0,
    resume_file_path VARCHAR2(500),
    status VARCHAR2(20) DEFAULT 'New',
    created_date TIMESTAMP DEFAULT SYSTIMESTAMP
);

-- Create Interviews table
CREATE TABLE interviews (
    interview_id VARCHAR2(50) PRIMARY KEY,
    candidate_id VARCHAR2(50) NOT NULL,
    job_id VARCHAR2(50) NOT NULL,
    recruiter_id VARCHAR2(50),
    interview_date_time TIMESTAMP,
    status VARCHAR2(20) DEFAULT 'Scheduled',
    feedback CLOB,
    rating NUMBER(3,1) DEFAULT 0.0,
    interview_type VARCHAR2(20),
    created_date TIMESTAMP DEFAULT SYSTIMESTAMP,
    CONSTRAINT fk_interview_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id),
    CONSTRAINT fk_interview_job FOREIGN KEY (job_id) REFERENCES jobs(job_id),
    CONSTRAINT fk_interview_recruiter FOREIGN KEY (recruiter_id) REFERENCES recruiters(recruiter_id)
);

-- Create Applications table
CREATE TABLE applications (
    application_id VARCHAR2(50) PRIMARY KEY,
    candidate_id VARCHAR2(50) NOT NULL,
    job_id VARCHAR2(50) NOT NULL,
    application_status VARCHAR2(20) DEFAULT 'Applied',
    applied_date DATE DEFAULT SYSDATE,
    CONSTRAINT fk_application_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id),
    CONSTRAINT fk_application_job FOREIGN KEY (job_id) REFERENCES jobs(job_id),
    CONSTRAINT unique_application UNIQUE (candidate_id, job_id)
);