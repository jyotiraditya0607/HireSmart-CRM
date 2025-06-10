# HireSmart - Recruitment Management System

A Java-based recruitment management system that helps recruiters manage job postings, candidates, interviews, and applications.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── hiresmart/
│               ├── config/           # Configuration classes
│               ├── dao/              # Data Access Objects
│               ├── exception/        # Custom exceptions
│               ├── model/            # Domain models
│               ├── service/          # Business logic
│               └── util/             # Utility classes
├── test/
│   └── java/
│       └── com/
│           └── hiresmart/
│               ├── dao/              # DAO tests
│               ├── service/          # Service tests
│               └── util/             # Utility tests
└── resources/
    ├── sql/                         # SQL scripts
    │   ├── schema.sql
    │   ├── data.sql
    │   └── oracle_schema.sql
    └── config/                      # Configuration files
        └── application.properties
```

## Features

- Recruiter management
- Job posting and management
- Candidate tracking
- Interview scheduling
- Application processing
- Database integration with Oracle

## Prerequisites

- Java 8 or higher
- Oracle Database 11g or higher
- Oracle JDBC Driver (ojdbc8.jar)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/hiresmart.git
   cd hiresmart
   ```

2. Download Oracle JDBC Driver:
   ```bash
   ./download_driver.sh
   ```

3. Configure database connection:
   - Update `src/main/resources/config/application.properties` with your database credentials
   - Or modify `src/main/java/com/hiresmart/config/DatabaseConfig.java`

4. Initialize the database:
   ```bash
   ./build.sh
   ```

## Running Tests

```bash
./test.sh
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 