package com.skiply.student.service;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.entity.StudentEntity;
import com.skiply.student.exception.ResourceNotFoundException;
import com.skiply.student.mapper.StudentMapper;
import com.skiply.student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper; // Use @Autowired for dependency injection

    // Save Student
    public StudentDto saveStudent(StudentDto studentDto) {
        logger.info("Saving student: {}", studentDto.studentName()); // Use record accessor

        // Convert DTO to Entity using the mapper
        StudentEntity studentEntity = studentMapper.dtoToEntity(studentDto);
        
        // Save the student entity
        StudentEntity savedEntity = studentRepository.save(studentEntity);

        // Convert back Entity to DTO and return
        logger.info("Student saved successfully: {}", savedEntity.getStudentId());
        return studentMapper.entityToDto(savedEntity);
    }

    // Get Student By ID
    public StudentDto getStudentById(int id) {
        logger.info("Fetching student with ID: {}", id);

        // Fetch the entity
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student with ID {} not found", id);
                    return new ResourceNotFoundException("Student with ID " + id + " not found");
                });

        // Convert Entity to DTO and return
        logger.info("Fetched student: {}", studentEntity.getStudentId());
        return studentMapper.entityToDto(studentEntity);
    }

    // Delete Student By ID
    public void deleteStudentById(int id) {
        logger.info("Deleting student with ID: {}", id);
        
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student with ID {} not found", id);
                    return new ResourceNotFoundException("Student with ID " + id + " not found");
                });

        studentRepository.delete(studentEntity);
        logger.info("Student with ID {} deleted successfully", id);
    }

    // Update Student
    public StudentDto updateStudent(int id, StudentDto updatedStudentDto) {
        logger.info("Updating student with ID: {}", id);

        // Fetch the existing student entity
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student with ID {} not found", id);
                    return new ResourceNotFoundException("Student with ID " + id + " not found");
                });

        // Use the mapper to update fields from DTO
        existingStudent.setStudentName(updatedStudentDto.studentName());
        existingStudent.setGrade(updatedStudentDto.grade());
        existingStudent.setMobileNumber(updatedStudentDto.mobileNumber());
        existingStudent.setSchoolName(updatedStudentDto.schoolName());

        // Save the updated entity
        StudentEntity updatedEntity = studentRepository.save(existingStudent);

        // Convert updated entity back to DTO and return
        logger.info("Student updated successfully: {}");
        return studentMapper.entityToDto(updatedEntity);
    }
}
