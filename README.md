# Airline Reservation System

A Java-based console application designed to automate and simplify airline ticket reservation operations. The system replaces manual booking procedures with a structured, object-oriented solution to manage flight records, passenger reservations, ticket cancellations, and report generation using file handling techniques.

## Group Details & Contributions

| Student ID  | Name        |
|-------------|-------------|
| PS/2023/369 | P.Sathana   |
| EC/2023/010 | CROOS A.R.J |
| EC/2023/016 | A.SHARANKAN |
| EC/2023/62  | T.THANUSKA  |
| EC/2023/63  | N.PRABALINI |

## Prerequisites

**Java Development Kit (JDK):** Version 8 or higher (Java 11, 17, or 21 recommended).

**Text Editor or IDE:** Any text editor or an Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or NetBeans.

## How to Run – Step-by-Step Commands

Since the application consists of standard Java files and provides a console-based menu interface, it can be compiled and executed directly through the terminal.

### Compile all Java classes:

```
javac *.java
```

### Execute the system entry point:

```
java Project
```

## Key Objectives & Features

### System Features

* **Menu-Driven Interface:** Provides an interactive console-based menu for booking, cancellation, searching flights, generating reports, and viewing available flights.
* **Flight Management:** Maintains flight details including flight number, origin, destination, and available seats.
* **Ticket Reservation:** Allows passengers to book tickets and automatically updates seat availability.
* **Ticket Cancellation:** Enables cancellation of existing bookings and restores the available seat count.
* **Flight Search:** Supports searching flights based on the destination using a recursive search mechanism.
* **File Handling:** Stores and retrieves flight details, bookings, cancellations, and reports using text files.
* **Report Generation:** Generates daily reports containing booking and cancellation records.

## OOP Principles Demonstrated

### Encapsulation

Implemented using private class variables and controlled access through public getter and setter methods in classes such as `Flight`, `Passenger`, and `Booking`.

### Abstraction

Achieved by separating complex operations into dedicated manager classes (`FlightManager`, `BookingManager`, and `ReportManager`), which provide simple methods for handling reservations, searches, and reports.

### Inheritance

The current system is designed using independent classes and does not implement inheritance, as the project requirements are fulfilled through composition and class interaction.

### Polymorphism

Polymorphism is demonstrated through method overriding of the `toString()` method in classes like `Flight`, `Passenger`, and `Booking` to provide customized string representations.

## System Architecture

The core program environment relies on the interaction of the following classes:

* **Project:** Main application entry point containing the menu-driven interface.
* **Flight:** Represents flight details including flight number, origin, destination, and available seats.
* **Passenger:** Stores passenger information.
* **Booking:** Establishes the relationship between a passenger and a flight reservation.
* **FlightManager:** Handles loading, saving, adding, and searching flight records.
* **BookingManager:** Manages ticket booking and cancellation processes.
* **ReportManager:** Generates reports based on booking and cancellation data.
* **FileUtils:** Provides reusable utility methods for reading and writing text files.
* **FlightFileWriter:** Creates initial sample flight records when no flight data file exists.

## Current Limitations & Future Scope

### Limitations

* Operates as a console-based application without a graphical user interface (GUI).
* Uses text files for data storage instead of a relational database system.
* Supports basic reservation operations without user authentication.
* Does not provide online payment, ticket printing, or real-time flight updates.

### Future Enhancements

* Integrating database systems such as MySQL or MongoDB for persistent and scalable data management.
* Developing a graphical user interface (GUI) using JavaFX or Swing.
* Adding user login and role-based access control for administrators and passengers.
* Implementing online payment systems and digital ticket generation.
* Adding real-time flight scheduling and notification services.
