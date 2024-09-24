package com.skiply.student.controller;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.exception.ResourceNotFoundException;
import com.skiply.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Test for adding a new student
    @Test
    void testAddStudent() {
        StudentDto studentDto = new StudentDto(null, "John Doe", null, null, null);
        
        when(studentService.saveStudent(any(StudentDto.class))).thenReturn(studentDto);

        ResponseEntity<StudentDto> response = studentController.addStudent(studentDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().studentName());
        verify(studentService, times(1)).saveStudent(any(StudentDto.class));
    }

    // 2. Test for getting a student by ID
    @Test
    void testGetStudentById() {
        StudentDto studentDto = new StudentDto(1, "John Doe", null, null, null);

        when(studentService.getStudentById(1)).thenReturn(studentDto);

        ResponseEntity<StudentDto> response = studentController.getStudent(1);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().studentName());
        verify(studentService, times(1)).getStudentById(1);
    }

    // 3. Test for student not found when getting by ID
    @Test
    void testGetStudentByIdNotFound() {
        when(studentService.getStudentById(1)).thenThrow(new ResourceNotFoundException("Student with ID 1 not found"));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentController.getStudent(1);
        });

        assertEquals("Student with ID 1 not found", exception.getMessage());
        verify(studentService, times(1)).getStudentById(1);
    }

    // 4. Test for deleting a student by ID
    @Test
    void testDeleteStudentById() {
        doNothing().when(studentService).deleteStudentById(1);

        ResponseEntity<String> response = studentController.deleteStudent(1);

        assertEquals(OK, response.getStatusCode());
        verify(studentService, times(1)).deleteStudentById(1);
    }

    // 5. Test for student not found when deleting by ID
    @Test
    void testDeleteStudentByIdNotFound() {
        doThrow(new ResourceNotFoundException("Student with ID 1 not found")).when(studentService).deleteStudentById(1);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentController.deleteStudent(1);
        });

        assertEquals("Student with ID 1 not found", exception.getMessage());
        verify(studentService, times(1)).deleteStudentById(1);
    }

    // 6. Test for updating a student by ID
    @Test
    void testUpdateStudent() {
        StudentDto studentDto = new StudentDto(null, "Jane Doe", null, null, null);

        when(studentService.updateStudent(eq(1), any(StudentDto.class))).thenReturn(studentDto);

        ResponseEntity<StudentDto> response = studentController.updateStudent(1, studentDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().studentName());
        verify(studentService, times(1)).updateStudent(eq(1), any(StudentDto.class));
    }

    // 7. Test for student not found when updating by ID
    @Test
    void testUpdateStudentNotFound() {
        StudentDto studentDto = new StudentDto(null, "Jane Doe", null, null, null);

        when(studentService.updateStudent(eq(1), any(StudentDto.class)))
            .thenThrow(new ResourceNotFoundException("Student with ID 1 not found"));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentController.updateStudent(1, studentDto);
        });

        assertEquals("Student with ID 1 not found", exception.getMessage());
        verify(studentService, times(1)).updateStudent(eq(1), any(StudentDto.class));
    }
}
