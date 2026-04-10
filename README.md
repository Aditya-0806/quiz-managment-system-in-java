# Advanced Student Quiz & Performance Analytics System

## Prerequisites
- Java JDK 8 or higher
- MySQL Server 8.0+
- MySQL Connector/J (JAR file)

## Setup Instructions

### Step 1: Database Setup
1. Open MySQL Workbench or terminal
2. Run the SQL script:
   ```sql
   source /path/to/database_setup.sql
   ```
   OR open MySQL terminal and paste the entire `database_setup.sql` file contents.

### Step 2: Add MySQL Connector JAR
1. Download `mysql-connector-j-8.x.x.jar` from https://dev.mysql.com/downloads/connector/j/
2. Place it in the `lib/` folder of this project.

### Step 3: Compile the Project
```bash
# From project root
javac -cp "lib/mysql-connector-j-8.x.x.jar" -d bin -sourcepath src src/com/quiz/Main.java
```

### Step 4: Run the Application
```bash
java -cp "bin;lib/mysql-connector-j-8.x.x.jar" com.quiz.Main
```
On Linux/Mac use `:` instead of `;` in classpath.

---

## Default Credentials
| Role    | Username/Email | Password |
|---------|----------------|----------|
| Admin   | admin          | password |
| Student | Register first | Your own |

## DB Config (pre-set in DBConnection.java)
| Field    | Value         |
|----------|---------------|
| Host     | localhost     |
| Port     | 3306          |
| Database | student_quiz  |
| Username | root          |
| Password | Adi@0806      |

---

## Project Structure
```
StudentQuizSystem/
├── src/
│   └── com/quiz/
│       ├── Main.java
│       ├── db/
│       │   ├── DBConnection.java
│       │   ├── StudentDAO.java
│       │   ├── QuestionDAO.java
│       │   └── ResultDAO.java
│       ├── model/
│       │   ├── Student.java
│       │   ├── Question.java
│       │   └── Result.java
│       ├── ui/
│       │   ├── LoginFrame.java
│       │   ├── RegisterFrame.java
│       │   ├── SubjectSelectionFrame.java
│       │   ├── QuizFrame.java
│       │   ├── ResultFrame.java
│       │   └── StudentHistoryFrame.java
│       ├── admin/
│       │   └── AdminDashboard.java
│       └── util/
│           └── UIHelper.java
├── lib/               ← Place mysql-connector JAR here
├── bin/               ← Compiled .class files go here
├── database_setup.sql
└── README.md
```

## Subjects Available
1. Java
2. Mathematics
3. Computer Networks
4. DBMS
5. Advanced Data Structures and Algorithms

## Features
- Student registration & login
- 5 subjects with 30 questions each (10 random per exam)
- 30-second timer per question with auto-advance
- Answer review after exam
- Performance history per student
- Admin dashboard: all results, all students, topper display
