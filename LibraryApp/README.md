# Spring Boot Database Assignment

This is a simple Spring Boot application built for my database assignment. It manages records for two entities (`Author` and `Book`) using Spring Data JPA and an H2 in-memory database.

## Project Overview

The app demonstrates a standard One-to-Many relationship (one author can have many books). I implemented the basic CRUD operations with a frontend using JSP. 

### What it does:
- **Auto-populates data:** When the app starts up, it automatically adds 10 dummy authors and 10 dummy books into the database so there's data to work with right away.
- **Add new records:** You can add a new author or book using the forms on the web interface. I added basic exception handling, so if you try to submit a book without selecting an author, it won't crash the server.
- **List records:** The home page fetches all authors and books from the DB and displays them in a table. The books list uses a custom JPQL inner join query in the repository to pull the author data efficiently.
- **Update records:** Clicking "Edit" next to an entry will load its current details into a form where you can update and save the changes.

## Setup & Running

It's a standard Maven project.

1. Clone this repository to your local machine.
2. Open the `LibraryApp` folder in your IDE (IntelliJ or Eclipse work best).
3. Run the `LibraryApplication.java` main class.
4. Open your browser and go to `http://localhost:8080/`

**Note:** Since it uses an H2 in-memory database, any new data you add will be wiped when you restart the application.

## Technologies Used
- **Java 17**
- **Spring Boot 3** (Spring Web, Spring Data JPA)
- **H2 Database**
- **JSP / JSTL** for the views
- **JUnit 5 / Mockito** for unit testing the service and repository layers
