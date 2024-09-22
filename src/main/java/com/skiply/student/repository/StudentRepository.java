package com.skiply.student.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

import com.skiply.student.entity.StudentEntity;

	public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
	}



