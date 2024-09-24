package com.skiply.student.dto;


public record StudentDto(
    Integer studentId,
    String studentName,
    String grade,
    Integer mobileNumber,
    String schoolName
) {
    // No additional methods or constructors are needed unless you want to add validation or other logic.
}
