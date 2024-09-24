package com.skiply.student.mapper;

import org.springframework.stereotype.Component;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.entity.StudentEntity;
@Component
public class StudentMapper {

    public StudentEntity dtoToEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setStudentId(dto.studentId()); // Use the record's getter
        entity.setStudentName(dto.studentName());
        entity.setGrade(dto.grade());
        entity.setMobileNumber(dto.mobileNumber());
        entity.setSchoolName(dto.schoolName());
        return entity;
    }

    public StudentDto entityToDto(StudentEntity entity) {
        // Use the record's constructor to create a new instance
        return new StudentDto(
            entity.getStudentId(),
            entity.getStudentName(),
            entity.getGrade(),
            entity.getMobileNumber(),
            entity.getSchoolName()
        );
    }
}
