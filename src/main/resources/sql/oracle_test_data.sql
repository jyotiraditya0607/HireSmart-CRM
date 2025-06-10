-- Insert sample recruiters
INSERT INTO recruiters (recruiter_id, name, email, phone, expertise, active_status, hire_count) VALUES 
('REC001', 'John Smith', 'john.smith@hiresmart.com', '+1-555-0101', 'Technology,Software', 1, 15);
INSERT INTO recruiters (recruiter_id, name, email, phone, expertise, active_status, hire_count) VALUES 
('REC002', 'Sarah Johnson', 'sarah.johnson@hiresmart.com', '+1-555-0102', 'Finance,Banking', 1, 12);
INSERT INTO recruiters (recruiter_id, name, email, phone, expertise, active_status, hire_count) VALUES 
('REC003', 'Mike Davis', 'mike.davis@hiresmart.com', '+1-555-0103', 'Healthcare,Medical', 1, 8);

-- Insert sample jobs
INSERT INTO jobs (job_id, title, description, requirements, location, salary_min, salary_max, status, recruiter_id) VALUES 
('JOB001', 'Senior Java Developer', 'Experienced Java developer needed for enterprise applications', 
'Java, Spring, MySQL, 5+ years exp', 'New York', 80000, 120000, 'Open', 'REC001');

INSERT INTO jobs (job_id, title, description, requirements, location, salary_min, salary_max, status, recruiter_id) VALUES 
('JOB002', 'Financial Analyst', 'Analyze financial data and trends for investment decisions', 
'Excel, SQL, Finance degree, 3+ years', 'Chicago', 60000, 90000, 'Open', 'REC002');

-- Insert sample candidates
INSERT INTO candidates (candidate_id, name, email, phone, skills, experience_years, status) VALUES 
('CAN001', 'Alice Brown', 'alice.brown@email.com', '+1-555-0201', 'Java,Spring,Hibernate', 6, 'Active');

INSERT INTO candidates (candidate_id, name, email, phone, skills, experience_years, status) VALUES 
('CAN002', 'Bob Wilson', 'bob.wilson@email.com', '+1-555-0202', 'Financial Analysis,Excel,SQL', 4, 'Active');

-- Insert sample applications
INSERT INTO applications (application_id, candidate_id, job_id, application_status) VALUES 
('APP001', 'CAN001', 'JOB001', 'Applied');

INSERT INTO applications (application_id, candidate_id, job_id, application_status) VALUES 
('APP002', 'CAN002', 'JOB002', 'Under Review');

-- Insert sample interviews
INSERT INTO interviews (interview_id, candidate_id, job_id, recruiter_id, interview_date_time, status, interview_type) VALUES 
('INT001', 'CAN001', 'JOB001', 'REC001', SYSTIMESTAMP + INTERVAL '1' DAY, 'Scheduled', 'Technical');

INSERT INTO interviews (interview_id, candidate_id, job_id, recruiter_id, interview_date_time, status, interview_type) VALUES 
('INT002', 'CAN002', 'JOB002', 'REC002', SYSTIMESTAMP + INTERVAL '2' DAY, 'Scheduled', 'HR');

-- Commit the transactions
COMMIT; 