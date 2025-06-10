-- Sample data for recruiters
INSERT INTO recruiters VALUES 
('REC001', 'John Smith', 'john.smith@hiresmart.com', '+1-555-0101', 'Technology,Software', TRUE, 15, NOW()),
('REC002', 'Sarah Johnson', 'sarah.johnson@hiresmart.com', '+1-555-0102', 'Finance,Banking', TRUE, 12, NOW()),
('REC003', 'Mike Davis', 'mike.davis@hiresmart.com', '+1-555-0103', 'Healthcare,Medical', TRUE, 8, NOW()),
('REC004', 'Emily Chen', 'emily.chen@hiresmart.com', '+1-555-0104', 'Marketing,Digital', TRUE, 10, NOW()),
('REC005', 'David Wilson', 'david.wilson@hiresmart.com', '+1-555-0105', 'Sales,Business', TRUE, 20, NOW());

-- Sample data for jobs
INSERT INTO jobs VALUES 
('JOB001', 'Senior Java Developer', 'Experienced Java developer needed for enterprise applications', 'Java, Spring, MySQL, 5+ years exp', 'New York', 80000, 120000, 'Open', 'REC001', CURRENT_DATE),
('JOB002', 'Financial Analyst', 'Analyze financial data and trends for investment decisions', 'Excel, SQL, Finance degree, 3+ years', 'Chicago', 60000, 90000, 'Open', 'REC002', CURRENT_DATE),
('JOB003', 'Registered Nurse', 'Patient care in hospital setting with focus on critical care', 'RN license, 2+ years experience', 'Boston', 65000, 85000, 'Open', 'REC003', CURRENT_DATE),
('JOB004', 'Digital Marketing Manager', 'Lead digital marketing campaigns and strategy', 'SEO, Social Media, Analytics, 4+ years', 'San Francisco', 70000, 100000, 'Open', 'REC004', CURRENT_DATE),
('JOB005', 'Sales Representative', 'B2B sales role focusing on enterprise clients', 'Sales experience, CRM tools, 3+ years', 'Los Angeles', 50000, 80000, 'Open', 'REC005', CURRENT_DATE);

-- Sample data for candidates
INSERT INTO candidates VALUES 
('CAN001', 'Alice Brown', 'alice.brown@email.com', '+1-555-0201', 'Java,Spring,Hibernate', 6, '/resumes/alice_brown.pdf', 'Active', NOW()),
('CAN002', 'Bob Wilson', 'bob.wilson@email.com', '+1-555-0202', 'Financial Analysis,Excel,SQL', 4, '/resumes/bob_wilson.pdf', 'Active', NOW()),
('CAN003', 'Carol Martinez', 'carol.martinez@email.com', '+1-555-0203', 'Nursing,Patient Care,ICU', 3, '/resumes/carol_martinez.pdf', 'Active', NOW()),
('CAN004', 'David Lee', 'david.lee@email.com', '+1-555-0204', 'Digital Marketing,SEO,SEM', 5, '/resumes/david_lee.pdf', 'Active', NOW()),
('CAN005', 'Eva Garcia', 'eva.garcia@email.com', '+1-555-0205', 'Sales,B2B,CRM', 4, '/resumes/eva_garcia.pdf', 'Active', NOW());

-- Sample data for applications
INSERT INTO applications VALUES 
('APP001', 'CAN001', 'JOB001', 'Applied', CURRENT_DATE),
('APP002', 'CAN002', 'JOB002', 'Under Review', CURRENT_DATE),
('APP003', 'CAN003', 'JOB003', 'Shortlisted', CURRENT_DATE),
('APP004', 'CAN004', 'JOB004', 'Applied', CURRENT_DATE),
('APP005', 'CAN005', 'JOB005', 'Under Review', CURRENT_DATE);

-- Sample data for interviews
INSERT INTO interviews VALUES 
('INT001', 'CAN001', 'JOB001', 'REC001', '2024-03-15 10:00:00', 'Scheduled', NULL, 0.0, 'Technical', NOW()),
('INT002', 'CAN002', 'JOB002', 'REC002', '2024-03-16 14:00:00', 'Scheduled', NULL, 0.0, 'HR', NOW()),
('INT003', 'CAN003', 'JOB003', 'REC003', '2024-03-17 11:00:00', 'Scheduled', NULL, 0.0, 'Technical', NOW()),
('INT004', 'CAN004', 'JOB004', 'REC004', '2024-03-18 15:00:00', 'Scheduled', NULL, 0.0, 'HR', NOW()),
('INT005', 'CAN005', 'JOB005', 'REC005', '2024-03-19 13:00:00', 'Scheduled', NULL, 0.0, 'Technical', NOW());

-- Add some completed interviews with feedback
INSERT INTO interviews VALUES 
('INT006', 'CAN001', 'JOB002', 'REC002', '2024-03-10 10:00:00', 'Completed', 'Strong technical skills, good communication', 4.5, 'Technical', NOW()),
('INT007', 'CAN002', 'JOB003', 'REC003', '2024-03-11 14:00:00', 'Completed', 'Excellent experience in healthcare', 4.8, 'Technical', NOW()),
('INT008', 'CAN003', 'JOB004', 'REC004', '2024-03-12 11:00:00', 'Completed', 'Good marketing knowledge, needs more digital experience', 3.5, 'Technical', NOW());

-- Add some applications with different statuses
INSERT INTO applications VALUES 
('APP006', 'CAN001', 'JOB002', 'Rejected', '2024-03-01'),
('APP007', 'CAN002', 'JOB003', 'Accepted', '2024-03-02'),
('APP008', 'CAN003', 'JOB004', 'Under Review', '2024-03-03');

-- Add some jobs with different statuses
INSERT INTO jobs VALUES 
('JOB006', 'Senior DevOps Engineer', 'Looking for experienced DevOps engineer', 'AWS, Docker, Kubernetes, 4+ years', 'Seattle', 90000, 130000, 'Closed', 'REC001', '2024-02-01'),
('JOB007', 'Product Manager', 'Product management role for SaaS platform', 'Agile, Product Strategy, 5+ years', 'Austin', 85000, 120000, 'On Hold', 'REC002', '2024-02-15'); 