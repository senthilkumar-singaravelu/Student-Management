package com.skiply.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skiply.student.entity.StudentEntity;
import com.skiply.student.dto.StudentDto;
import com.skiply.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @Operation(summary = "Add a new student")
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@Parameter(description = "Student data")@RequestBody StudentDto studentDto) {
        // Call service and pass DTO, expecting a DTO as response
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        return ResponseEntity.ok(savedStudent);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@Parameter(description = "Display Student data")@PathVariable int id) {
        // Call service to get DTO
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@Parameter(description = "Delete Student data")@PathVariable int id) {
        // Call service to delete
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@Parameter(description = "Update Student data")@PathVariable int id, @RequestBody StudentDto studentDto) {
        // Call service to update and pass DTO, expect a DTO in response
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }
}
