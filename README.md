# A Java Swing-based desktop application for managing student grades.
The system allows users to input student data, calculate statistics, and generate a summary report through a graphical user interface.

 # Features

Add multiple students

Store grades using ArrayList

Calculate:

Average score

Highest score

Lowest score

Generate a summary report for all students

User-friendly GUI built using Java Swing

 # Technologies Used

Java (JDK 8 or above)

Java Swing (GUI framework)

ArrayList (Data Structure)

OOP Principles

# Project Structure
StudentGradeGUI.java

Main Components:

Student class → Handles student data and grade calculations

StudentGradeGUI class → Manages GUI and user interactions

 * How to Run *
1. Clone the Repository
git clone https://github.com/ManojGojo/codealpha_tasks.git
2. Compile the Program
javac StudentGradeGUI.java
   3. Run the Program
java StudentGradeGUI
 * How to Use *

Enter the student's name.

Enter grades separated by commas (Example: 85, 90, 78, 88).

Click Add Student.

Click Generate Report to view the complete summary.

@ Sample Output (Report Section)
===== STUDENT SUMMARY REPORT =====

Student Name: John
Grades: [85.0, 90.0, 78.0, 88.0]
Average: 85.25
Highest: 90.00
Lowest: 78.00
--------------------------------------------------
@ Learning Objectives

This project demonstrates:

Object-Oriented Programming (Encapsulation & Class Design)

GUI development using Swing

Event handling in Java

Data management using ArrayList

Basic statistical computation

  ** Possible Enhancements

Grade classification (A/B/C/Fail)

Edit/Delete student functionality

Data persistence using file handling

JTable-based structured report

Sorting students by average score
# License

This project is open-source and available for educational purposes.

If you'd like, I can also provide:

A more polished README with badges

Screenshots section template

Project report documentation (for submission)

A professional project description for LinkedIn

Tell me where you're planning to publish it (GitHub only or portfolio too), and I’ll tailor it properly.

PROJECT NUMBER - 2

# Stock Trading Simulation (Java)

A basic stock trading environment developed in Java using Object-Oriented Programming principles.
The application simulates stock market price changes, allows users to perform buy and sell operations, and tracks portfolio performance over time.

# Features
Market Simulation

Simulates dynamic stock price fluctuations each time the menu refreshes.

Buy and Sell Operations

Users can purchase and sell stocks based on available balance and holdings.

Portfolio Management

Tracks account balance, stock holdings, and calculates total portfolio value.

Transaction History

Maintains a record of all buy and sell operations.

Data Persistence

Supports saving portfolio data using file serialization.

# Technologies Used
Java

Core language implementation.

OOP Concepts

Encapsulation, class structure, modular design.

Java Collections

HashMap for holdings and ArrayList for transactions.

File I/O

Object serialization for saving portfolio data.

Project Structure
Single File Implementation

StockTradingApp.java

Includes the following classes within one file:

# Stock
Transaction
Portfolio
Market
Main Application Controller

# How to Run
Compile
javac StockTradingApp.java
Execute
java StockTradingApp
Learning Outcomes
Object-Oriented Design

# Structured separation of responsibilities within a single file.

Portfolio Performance Tracking

Calculation of total asset value and balance.

Market Simulation Logic

Randomized stock price updates.
