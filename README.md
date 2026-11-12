
# CampusHubAI: Student & Admin Portal

**CampusHubAI** is a terminal-based Console Application designed to automate campus workflows for university students and administrators. Powered by a robust MySQL relational database layer, it replaces manual academic administration tasks with an interactive, secure, and fast console-based ecosystem.

---

## 🚀 Key Features

### 👨‍🎓 Student Dashboard
* **Attendance Viewer**: Subject-wise present/total classes with automatic percentage calculations.
* **Isolated Timetable**: Real-time lecture schedules filtered specifically to display only matching classes of the student's department.
* **Announcements**: Broadcast board displaying general notices from admins chronologically.
* **Club Management**: Discover active clubs and register for memberships.
* **Event Registrations**: View and register for upcoming events, with a dedicated **Search Event by ID** option.
* **Notes Sharing**: Download requested notes uploaded by peers.
* **Complaints System**: Submit grievances and track resolution status.

### 🛡️ Admin Dashboard
* **Attendance Management**: View and update attendance metrics for any student.
* **Announcement Broadcasts**: Create general notices broadcast immediately to all students.
* **Notes Requests Moderation**: Review student notes uploads and approve or reject submissions.
* **Grievance Resolution**: View submitted student complaints and mark them as resolved.
* **Club & Event Management**: Create, delete, search, and manage clubs/events (including searching events by ID).

---

## 🏛️ Architecture & Software Design

The project is structured around clean coding principles and design patterns:

1. **Layered Design Pattern**:
   * **Model Layer**: Plain Old Java Objects (POJOs) mapping the SQL tables (e.g., `Student`, `Admin`, `Event`, `Timetable`).
   * **Data Access Object (DAO) Layer**: Handles JDBC SQL execution and decouples database queries from business rules.
   * **Service Layer**: Coordinates business workflows and manages console interactions.
   * **Menu/Dashboard Layer**: Renders interactive text menus and processes routing.

2. **Reconnecting Proxy Pattern**:
   * Idiomatic Java Reflection Proxy monitors the MySQL database connection.
   * Prevents standard idle-timeout crashes by transparently pinging the server (`isClosed()` and `isValid(2)` checks) before running any query. If the socket was closed, it opens a new connection in the background.

3. **Safe Input Parsing**:
   * Safe wrapper around `Scanner` to capture and clear buffer errors (such as `InputMismatchException` from typing alphabetical characters into numeric fields) to keep the CLI console running without crash events.

4. **Triggers, Procedures & Functions**:
   * Includes automated SQLite/MySQL migrations at startup.
   * Leverages Database triggers to automatically record the resolution date for student complaints.
   * Employs MySQL procedures with cursors to generate attendance shortage reports for students falling below the 75% threshold.

---

## 📂 Project Directory Structure

```text
CampusHubAi/
│
├── database/
│   └── campushub.sql             # SQL DB script containing schemas & sample data
├── lib/
│   └── mysql-connector-j-9.7.0.jar # MySQL JDBC Driver
├── src/
│   └── com/campushub/
│       ├── Datastructure/        # Data structures used in the app
│       ├── dao/                  # Data Access Object files (Database operations)
│       ├── dashboard/            # Student and Admin Dashboards
│       ├── database/             # DB Connection Proxy & Migration handlers
│       ├── main/                 # Main entrance class (CampusHubAI)
│       ├── menu/                 # CLI routing menus (MainMenu)
│       ├── model/                # Model classes (POJOs)
│       ├── service/              # Core business services & menu interaction scripts
│       └── utils/                # Input scanner utilities & Console outputs
│
├── run.bat                       # Compilation and execution batch script (Windows)
└── CampusHubAI_Documentation.html# HTML report containing documentation
```

---

## ⚙️ Setup & Installation

### Prerequisites
* **Java Development Kit (JDK)**: version 11 or higher (configured and added to system path).
* **MySQL Server**: Installed and running locally.

### 1. Database Setup
1. Open your MySQL client (e.g. MySQL Command Line Client, Workbench, or phpMyAdmin).
2. Import the database schema and sample data:
   ```sql
   SOURCE database/campushub.sql;
   ```
   *Alternatively, create a database named `CampusHubAI` and run the script inside it.*

### 2. Configure Database Credentials
Open `DatabaseConnection.java` and modify the database credentials if necessary:
```java
private static final String URL = "jdbc:mysql://localhost:3306/CampusHubAI";
private static final String USERNAME = "root"; // Update with your MySQL username
private static final String PASSWORD = "";     // Update with your MySQL password
```

### 3. Build & Run
#### On Windows:
Simply double-click or run `run.bat` from the command line:
```cmd
run.bat
```
The script will compile the Java classes automatically into the `out` directory and run the application.

#### Manual Commands (Any Platform):
To compile and run manually from the project root:

1. Create output directory:
   ```bash
   mkdir -p out/production/CampusHubAi
   ```
2. Compile the source code:
   ```bash
   javac -cp "lib/mysql-connector-j-9.7.0.jar" -d "out/production/CampusHubAi" src/com/campushub/main/CampusHubAI.java src/com/campushub/menu/*.java src/com/campushub/dashboard/*.java src/com/campushub/service/*.java src/com/campushub/dao/*.java src/com/campushub/model/*.java src/com/campushub/database/*.java src/com/campushub/utils/*.java src/com/campushub/Datastructure/*.java
   ```
3. Run the application:
   ```bash
   java -cp "out/production/CampusHubAi:lib/mysql-connector-j-9.7.0.jar" com.campushub.main.CampusHubAI
   ```
   *(Note: Use a semicolon `;` instead of a colon `:` as classpath separator on Windows).*

---

## 🔑 Default Credentials (Sample Data)

To test the application immediately, use the following credentials from the sample dataset:

### Admin Login
* **Email**: `admin@campushub.com`
* **Password**: `admin123`

### Student Login
* **Email**: `24ce001@college.edu`
* **Password**: `123456`
```
