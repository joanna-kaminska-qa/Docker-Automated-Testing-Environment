# Docker - Automated Testing Environment

![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Environment-2496ED?style=flat&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=flat&logo=postgresql&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-Grid-43B02A?style=flat&logo=selenium&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?style=flat&logo=junit5&logoColor=white)
![Testcontainers](https://img.shields.io/badge/Testcontainers-Integration-orange?style=flat)
![Gradle](https://img.shields.io/badge/Gradle-Build-02303A?style=flat&logo=gradle&logoColor=white)
![Status](https://img.shields.io/badge/Status-Containerized-brightgreen?style=flat)

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
---
## License

This project is licensed under the **MIT License**.  
See the LICENSE file for details.

---

## Authors

Created by:

**Joanna Kamińska**  
GitHub: [https://github.com/joanna-kaminska-qa](https://github.com/joanna-kaminska-qa)