package com.skiply.student.dto;


public class StudentDto {

   private Integer studentId;
    private String studentName;
    private String grade;
    private Integer mobileNumber;
    private String schoolName;


    public StudentDto() {}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public Integer getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

    // Getters and Setters
}
