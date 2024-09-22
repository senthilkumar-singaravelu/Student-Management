package com.skiply.student.mapper;

import com.skiply.student.dto.StudentDto;
import com.skiply.student.entity.StudentEntity;


public class StudentMapper {
    public StudentEntity dtoToEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setStudentId(dto.getStudentId()); // Ensure ID is set
        entity.setStudentName(dto.getStudentName());
        entity.setGrade(dto.getGrade());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setSchoolName(dto.getSchoolName());
        return entity;
    }

    public StudentDto entityToDto(StudentEntity entity) {
        StudentDto dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setStudentName(entity.getStudentName());
        dto.setGrade(entity.getGrade());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setSchoolName(entity.getSchoolName());
        return dto;
    }
}