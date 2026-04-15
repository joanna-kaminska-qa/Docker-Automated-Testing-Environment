# Docker - Automated Testing Environment

This project is a robust, containerized environment for automated testing of a **Habit Tracker** application. It leverages **Docker**, **Testcontainers**, and **Selenium Grid** to ensure high-quality integration and UI testing.

---

## Tech Stack

* **Language:** Java 21
* **Build Tool:** Gradle
* **Database:** PostgreSQL 15 (Containerized)
* **Containerization:** Docker & Docker Compose
* **Testing Frameworks:** JUnit 5, Testcontainers, Selenium WebDriver

---

## System Architecture

The environment consists of several interconnected services managed by Docker Compose:
1.  **moje-automaty-selenium:** The core Java testing suite.
2.  **baza-projektu (PostgreSQL):** A persistent database for development.
3.  **panel-adminer:** A web-based database management tool.
4.  **przegladarka-robot (Selenium Chrome):** A standalone Chrome instance for UI tests.

---

## Key Features

### Optimized Docker Layers
The `Dockerfile` is engineered with **layer caching** in mind. Dependencies are resolved separately from the source code, reducing build times from minutes to seconds during active development.

### Smart Integration Testing
Utilizes **Testcontainers** for database testing:
* **Isolation:** Every test run starts with a fresh, disposable PostgreSQL instance.
* **Initialization:** Automatic schema creation via `init.sql` scripts.
* **Validation:** Direct JDBC checks (`DatabaseManager`) to verify database state after UI actions.

### Automated Artifact Mapping
Configured **Docker Volumes** allow seamless transfer of test artifacts (like screenshots) from the container directly to the host machine's directory in real-time.

---

## How to Run

### Prerequisites
* Docker Desktop installed and running.

### Launching the Environment
To build and start the entire testing suite along with the database and Selenium Grid:

```bash
docker-compose -f docker-compose-selenium.yml up --build
```
### Accessing Tools
- Adminer (DB UI): http://localhost:8888
- Selenium VNC (Live Preview): http://localhost:7900 (Password: secret)
- Selenium Grid Hub: http://localhost:4444

### Maintenance
To keep the Docker environment clean and free up disk space from old test containers:

```bash
docker system prune -f
```

## License

This project is licensed under the **MIT License**.  
See the LICENSE file for details.

---

## Authors

Created by:

**Joanna Kamińska**  
GitHub: [https://github.com/joanna-kaminska-qa](https://github.com/joanna-kaminska-qa)