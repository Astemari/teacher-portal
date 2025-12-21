# Teacher’s Portal

<p align="center">
  <img src="assets/branding/logo (2).png" width="120" />
</p>

## Overview

Teacher’s Portal is a Java-based desktop application for educational management. It allows administrators and teachers to manage classes, students, attendance, assignments, grades, and communication, all through a user-friendly Swing GUI. Data is stored in CSV files for easy portability and transparency.

---

## How the Project Works

- **User Roles:**
  - **Admin:** Can add teachers, manage users, and oversee the system.
  - **Teacher:** Can enroll students, manage assignments, record grades, and track attendance.
  - **Student:** (if implemented) Can view their grades and assignments.

- **Authentication:**
  - Admin and teacher credentials are stored in CSV files (e.g., `Admin.csv`).
  - Login screens validate users against these files.

- **Data Management:**
  - All user, attendance, and grade data is stored in CSV files in the project root.
  - The application reads and writes to these files using custom Java methods.

- **Assignments & Grades:**
  - Teachers can create assignments, quizzes, and finals, and record grades for each student.
  - Attendance and marks are tracked per class and section.

- **Communication:**
  - The app can send emails and attachments to students using JavaMail (requires configuration).

- **GUI:**
  - Built with Java Swing and NetBeans GUI Builder (AbsoluteLayout).
  - Forms for login, enrollment, assignment creation, and more.

---

## Tech Stack

- **Language:** Java 21
- **Build Tool:** Apache Ant
- **GUI:** Java Swing (NetBeans AbsoluteLayout)
- **Email:** JavaMail (javax.mail, activation)
- **Data Storage:** CSV files (no database required)
- **IDE:** NetBeans (recommended for GUI editing)

---

## Project Structure

- `src/` — Java source code (organized by package)
- `lib/` — External libraries (JARs: mail.jar, activation.jar, AbsoluteLayout.jar)
- `Admin.csv` — Admin user data
- `build.xml` — Ant build script
- `dist/` — Output JAR
- `assets/branding/` — Logo and color palette

---

## Setup & Usage

1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd teacher-portal
   ```
2. **Add required JARs to `lib/`:**
   - `mail.jar` (JavaMail 1.6.x)
   - `activation.jar` (Activation 1.1.x)
   - `AbsoluteLayout.jar` (NetBeans AbsoluteLayout)
3. **Build the project:**
   ```bash
   ant jar
   ```
4. **Run the application:**
   ```bash
   java -cp "dist/Teacher_Portal.jar:lib/*" teacher_portal_GUI.Main
   ```

---

## Contributing
See `CONTRIBUTING.md` for guidelines. All contributions are welcome!

## License
MIT License — see `LICENSE` for details.

## Contact
- GitHub Organization: [Astemari](#)
- Email: _(to be added)_

> “Building tools for better learning.”

