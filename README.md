# Student Management API

## Overview

The Student Management API provides a RESTful interface to manage student records, including the ability to create, read, update, and delete student information. The API uses Spring Boot and H2 in-memory database for storage, with Swagger UI for API documentation.

## Features

- **Create Student**: Add a new student record.
- **Get Student**: Retrieve student details by ID.
- **Update Student**: Modify an existing student record.
- **Delete Student**: Remove a student record from the database.

## Technologies Used

- Spring Boot 3.3
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI for Swagger UI
- Maven for dependency management

## API Endpoints

### 1. Create Student

- **URL**: `/api/v1/students`
- **Method**: `POST`
- **Request Body**:

  ```json
  {
      "studentId": 1,
      "studentName": "John Doe",
      "grade": "10",
      "mobileNumber": 1234567890,
      "schoolName": "Springfield High"
  }

 Response 201 Created

### 2. Get Student

- **URL**: `/api/v1/students/{studentId}`
- **Method**: `GET`
- Response
{
    "studentName": "John Doe",
    "grade": "10",
    "mobileNumber": 1234567890,
    "schoolName": "Springfield High"
}

 Response Codes:
 200 OK
 404 Not Found (if student ID does not exist)

  
### 3. Update Student

- **URL**: `/api/v1/students/{studentId}`
- **Method**: `PUT`
- **Request Body**:

  ```json
  {
      "studentName": "John Doe",
      "grade": "11",
      "mobileNumber": 1234567890,
      "schoolName": "Springfield High"
  }

  Response: 200 OK
  
  
### 4. Delete Student

- **URL**: `/api/v1/students/{studentId}`
- **Method**: `DELETE`
- **Request Body**:

  ```json
  {
      "studentId": 1,
      "studentName": "John Doe",
      "grade": "11",
      "mobileNumber": 1234567890,
      "schoolName": "Springfield High"
  }
  
  Response: 204 NO CONTENT
  
### Swagger UI http://localhost:8080/swagger-ui.html
  
 ### API Spec : under resources studentspec.json