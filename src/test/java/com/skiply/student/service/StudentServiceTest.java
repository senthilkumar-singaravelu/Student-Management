package com.skiply.student.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skiply.student.entity.StudentEntity;
import com.skiply.student.exception.ResourceNotFoundException;
import com.skiply.student.mapper.StudentMapper;
import com.skiply.student.repository.StudentRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.entity.StudentEntity;
import com.skiply.student.exception.ResourceNotFoundException;
import com.skiply.student.repository.StudentRepository;
import com.skiply.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class StudentServiceTest {
	 @InjectMocks
	    private StudentService studentService;

	    @Mock
	    private StudentRepository studentRepository;

	    private StudentMapper studentMapper; // Add this for testing

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	        studentMapper = new StudentMapper(); // Initialize the mapper
	    }

	    @Test
	    public void testSaveStudent() {
	        StudentDto studentDto = new StudentDto();
	        studentDto.setStudentId(1);
	        studentDto.setStudentName("Jane Doe");
	        studentDto.setGrade("11");
	        studentDto.setMobileNumber(1234);

	        StudentEntity studentEntity = new StudentEntity();
	        studentEntity.setStudentId(1);
	        studentEntity.setStudentName("Jane Doe");
	        studentEntity.setGrade("11");
	        studentEntity.setMobileNumber(1234);

	        // Mock repository save method
	        when(studentRepository.save(any(StudentEntity.class))).thenReturn(studentEntity);

	        // Call the service method
	        StudentDto savedStudent = studentService.saveStudent(studentDto);

	        // Assert that savedStudent is not null and has the expected name
	        Assertions.assertNotNull(savedStudent, "Saved student should not be null");
	        Assertions.assertEquals("Jane Doe", savedStudent.getStudentName());
	    }
    @Test
    public void testUpdateStudent() {
        // Prepare the existing student entity in the DB
        StudentEntity existingStudent = new StudentEntity();
        existingStudent.setStudentId(1);
        existingStudent.setStudentName("John Doe");
        existingStudent.setGrade("10");
        existingStudent.setMobileNumber(1234);
        // Prepare the updated data (DTO)
        StudentDto updatedData = new StudentDto();
        updatedData.setStudentName("John Smith");
        updatedData.setGrade("11");
        updatedData.setMobileNumber(1234);

        // Mock repository findById and save methods
        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(existingStudent);

        // Call the service method
        StudentDto updatedStudent = studentService.updateStudent(1, updatedData);

        // Verify the result
        Assertions.assertEquals("John Smith", updatedStudent.getStudentName());
        Assertions.assertEquals("11", updatedStudent.getGrade());
    }

    @Test
    public void testUpdateNonExistingStudent() {
        // Prepare the updated data (DTO)
        StudentDto updatedData = new StudentDto();
        updatedData.setStudentName("Nonexistent Student");

        // Mock repository findById to return empty
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        // Expect a ResourceNotFoundException
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            studentService.updateStudent(99, updatedData);
        });
    }
}
