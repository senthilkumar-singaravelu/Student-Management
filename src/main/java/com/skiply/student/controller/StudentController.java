package com.skiply.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.service.StudentService;
import com.skiply.student.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Add a new student")
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(
            @Parameter(description = "Student data") @RequestBody StudentDto studentDto) {
        logger.info("Adding a new student: {}", studentDto.studentName());

        // Call service and pass DTO, expecting a DTO as response
        StudentDto savedStudent = studentService.saveStudent(studentDto);

        logger.info("Student added successfully: {}", savedStudent.studentName());
        return ResponseEntity.ok(savedStudent);
    }

    // Get student by ID
    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@Parameter(description = "Display Student data") @PathVariable int id) {
        logger.info("Fetching student with ID: {}", id);

        try {
            // Call service to get DTO
            StudentDto studentDto = studentService.getStudentById(id);
            logger.info("Fetched student: {}", studentDto.studentName());
            return ResponseEntity.ok(studentDto);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error fetching student: {}", ex.getMessage());
            throw ex; // Let the global exception handler manage this
        }
    }

    // Delete student
    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@Parameter(description = "Delete Student data") @PathVariable int id) {
        logger.info("Deleting student with ID: {}", id);

        try {
            // Call service to delete
            studentService.deleteStudentById(id);
            String successMessage = "Student with ID " + id + " deleted successfully";
            logger.info(successMessage);

            // Return a 200 OK response with the success message
            return ResponseEntity.ok(successMessage);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error deleting student: {}", ex.getMessage());
            throw ex; // Let the global exception handler manage this
        }
    }

    // Update student
    @Operation(summary = "Update student by ID")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @Parameter(description = "Update Student data") @PathVariable int id,
            @RequestBody StudentDto studentDto) {
        logger.info("Updating student with ID: {}", id);

        try {
            // Call service to update and pass DTO, expect a DTO in response
            StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
            logger.info("Student updated successfully: {}", updatedStudent.studentName());
            return ResponseEntity.ok(updatedStudent);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error updating student: {}", ex.getMessage());
            throw ex; // Let the global exception handler manage this
        }
    }
}
