package com.skiply.student.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skiply.student.entity.StudentEntity;

import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test

    public void testSaveAndFindStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1); // Set the ID manually
        studentEntity.setStudentName("Test Student");
        studentEntity.setGrade("10");
        studentEntity.setMobileNumber(1234567890);
        studentEntity.setSchoolName("Test School");

        // Save the entity using the repository
        studentRepository.save(studentEntity);

        // Retrieve the entity
        Optional<StudentEntity> foundStudent = studentRepository.findById(1);
        Assertions.assertTrue(foundStudent.isPresent());
        Assertions.assertEquals("Test Student", foundStudent.get().getStudentName());
    }

}

