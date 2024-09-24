package com.skiply.student.service;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.entity.StudentEntity;
import com.skiply.student.exception.ResourceNotFoundException;
import com.skiply.student.mapper.StudentMapper;
import com.skiply.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper; // This will be handled by Mockito

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // No need to initialize the mapper manually
    }

    @Test
    public void testSaveStudent() {
        // Create StudentDto using the record constructor
        StudentDto studentDto = new StudentDto(1, "Jane Doe", "11", 1234, null);
        
        // Create expected StudentEntity
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1);
        studentEntity.setStudentName("Jane Doe");
        studentEntity.setGrade("11");
        studentEntity.setMobileNumber(1234);
        
        // Mock the mapper's behavior
        when(studentMapper.dtoToEntity(studentDto)).thenReturn(studentEntity);
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(studentEntity);
        when(studentMapper.entityToDto(studentEntity)).thenReturn(studentDto);

        // Call the service method
        StudentDto savedStudent = studentService.saveStudent(studentDto);

        // Assert that savedStudent is not null and has the expected name
        assertNotNull(savedStudent, "Saved student should not be null");
        assertEquals("Jane Doe", savedStudent.studentName());
    }

    @Test
    public void testUpdateStudent() {
        // Prepare the existing student entity
        StudentEntity existingStudent = new StudentEntity();
        existingStudent.setStudentId(1);
        existingStudent.setStudentName("John Doe");
        existingStudent.setGrade("10");
        existingStudent.setMobileNumber(1234);
        
        // Prepare the updated data (DTO)
        StudentDto updatedData = new StudentDto(null, "John Smith", "11", 1234, null);

        // Mock repository findById and mapper methods
        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentMapper.dtoToEntity(updatedData)).thenReturn(existingStudent);
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(existingStudent);
        when(studentMapper.entityToDto(existingStudent)).thenReturn(updatedData); // Mock the return from entity to DTO

        // Call the service method
        StudentDto updatedStudent = studentService.updateStudent(1, updatedData);

        // Verify the result
        assertEquals("John Smith", updatedStudent.studentName());
        assertEquals("11", updatedStudent.grade());
    }

    @Test
    public void testGetStudentById() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1);
        studentEntity.setStudentName("John Doe");
        studentEntity.setMobileNumber(11111111);

        when(studentRepository.findById(1)).thenReturn(Optional.of(studentEntity));
        when(studentMapper.entityToDto(studentEntity)).thenReturn(new StudentDto(1, "John Doe", null, 11111111, null));

        StudentDto studentDto = studentService.getStudentById(1);

        assertNotNull(studentDto);
        assertEquals("John Doe", studentDto.studentName());
    }

    @Test
    public void testUpdateNonExistingStudent() {
        StudentDto updatedData = new StudentDto(null, "Nonexistent Student", null, null, null);
        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.updateStudent(99, updatedData);
        });

        assertEquals("Student with ID 99 not found", exception.getMessage());
        verify(studentRepository, times(1)).findById(99);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testDeleteStudentById() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1);
        studentEntity.setStudentName("John Doe");

        when(studentRepository.findById(1)).thenReturn(Optional.of(studentEntity));

        studentService.deleteStudentById(1);

        verify(studentRepository, times(1)).delete(studentEntity);
    }

    @Test
    public void testGetStudentByIdNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(1);
        });

        assertEquals("Student with ID 1 not found", exception.getMessage());
        verify(studentRepository, times(1)).findById(1);
        verifyNoMoreInteractions(studentRepository);
    }
}
