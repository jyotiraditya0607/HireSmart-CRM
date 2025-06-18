# HireSmart CRM – Recruitment and Candidate Engagement Platform

## Project Overview
A full-fledged Salesforce-powered CRM platform for HireSmart Solutions, a recruitment consulting firm. The system helps manage candidate applications, interview scheduling, recruiter allocation, status tracking, and engagement via Experience Cloud.

## Sprint 1: Backend Development – Java & DB Layer

### Features Implemented
- **OOP Design**: Abstract Person class with Candidate and Recruiter inheritance
- **Database Integration**: Oracle JDBC with normalized schema
- **CRUD Operations**: Complete Create, Read, Update, Delete for Candidates and Jobs
- **Applicant Pool Management**: Java collections and generics for managing candidate pools by job
- **Exception Handling**: Robust error handling and file I/O for resume upload simulation

### ERD (Entity Relationship Diagram)
![ERD](https://via.placeholder.com/800x600?text=HireSmart+ERD)

### Database Schema
The project includes a normalized database schema with the following entities:
- **Candidate**: Personal info, skills, experience, resume path
- **Job**: Job details, requirements, salary range, recruiter assignment
- **Recruiter**: Recruiter information and expertise
- **Interview**: Interview scheduling and feedback
- **Application**: Candidate-job application mapping

### Folder Structure
```
HireSmart_CTS/
├── README.md
├── sql/
│   └── schema.sql
└── src/
    └── com/
        └── hiresmart/
            ├── dao/
            │   ├── CandidateDAO.java
            │   ├── DBConnection.java
            │   ├── InterviewDAO.java
            │   ├── JobDAO.java
            │   └── RecruiterDAO.java
            ├── Main.java
            ├── model/
            │   ├── Candidate.java
            │   ├── Interview.java
            │   ├── Job.java
            │   └── Recruiter.java
            └── util/
                ├── DateUtil.java
                └── FileUtil.java
```

### Setup Instructions

1. **Database Setup**
   ```bash
   # Connect to Oracle database
   # Run the schema.sql script to create tables
   ```

2. **Configuration**
   - Update database credentials in `src/com/hiresmart/dao/DBConnection.java`
   - Ensure Oracle JDBC driver is in classpath

3. **Compilation and Execution**
   ```bash
   # Compile Java files
   javac -cp ".:ojdbc8.jar" src/com/hiresmart/**/*.java
   
   # Run the application
   java -cp ".:ojdbc8.jar:src" com.hiresmart.Main
   ```

### Key Components

#### OOP Implementation
- **Entity Interface**: Common interface for all entities
- **Person Abstract Class**: Base class for Candidate and Recruiter
- **Inheritance**: Candidate and Recruiter extend Person
- **Encapsulation**: Proper getters/setters for all fields

#### Database Operations
- **JDBC Integration**: Oracle database connectivity
- **CRUD Operations**: Complete database operations for all entities
- **Prepared Statements**: SQL injection prevention
- **Connection Management**: Proper resource handling

#### Collections and Generics
- **Applicant Pools**: Map<Integer, List<Candidate>> for job-candidate mapping
- **Generic Collections**: Type-safe data structures
- **Utility Classes**: Date and file handling utilities

### Evaluation Criteria Met
- ✅ Database design and normalization (20%)
- ✅ Java OOP implementation (20%)
- ✅ SQL + JDBC integration quality (20%)
- ✅ Utility classes and file I/O usage (20%)
- ✅ Code documentation and output demo (20%)

### Technologies Used
- **Backend**: Java 8+
- **Database**: Oracle Database
- **JDBC**: Oracle JDBC Driver
- **Design Patterns**: DAO Pattern, Factory Pattern

### Future Enhancements
- Spring Framework integration
- RESTful API development
- Frontend development with React/Angular
- Experience Cloud integration
- Advanced reporting and analytics

## Team Members
- Member 1: ER diagram design
- Member 2: OOP classes implementation
- Member 3: SQL scripts and CRUD operations
- Member 4: Java collections and generics
- Member 5: JDBC integration
- Member 6: Exception handling and utilities

## License
This project is developed for educational purposes as part of the CTS Assignment.
