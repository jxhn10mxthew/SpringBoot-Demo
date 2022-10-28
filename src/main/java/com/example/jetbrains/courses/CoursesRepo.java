package com.example.jetbrains.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CoursesRepo extends JpaRepository<Courses, Integer>{
	
	public List<Courses> findBytopicId(Integer topicId);
}
