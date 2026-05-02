# Database Assignment: Library Management System

This repository contains my submission for the database management assignment. It's a Spring Boot application that manages two entities: `Author` and `Book`.

## What's in here?

- `LibraryApp/` - This folder contains the actual Spring Boot project code.
- `Submission.pdf` - The final report containing screenshots, the ER diagram, and explanations of the code.

## About the Project

The application implements standard CRUD operations using Spring Data JPA and an H2 in-memory database. 

- It uses a One-to-Many relationship (one author writes multiple books).
- When you start the app, it automatically populates the database with 10 dummy authors and 10 dummy books so it's not empty.
- The frontend is built with simple JSP pages.
- I used a custom JPQL query for fetching books and authors together to avoid N+1 query problems.
- I added some basic exception handling to catch bad form submissions (like trying to add a book without picking an author).

## How to run

1. Open the `LibraryApp` folder in your IDE (like IntelliJ or Eclipse) as a Maven project.
2. Run `LibraryApplication.java`.
3. Open `http://localhost:8080/` in your browser.
