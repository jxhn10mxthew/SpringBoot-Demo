package com.example.jetbrains.courses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CoursesService {
	
	@Autowired
	private CoursesRepo courseRepository;
	
	
	public List<Courses> getallCourses(Integer topicId){
		List<Courses> courses= new ArrayList<>();
		courseRepository.findBytopicId(topicId)
		.forEach(courses::add);
		return courses;
	}
	public Courses getCourse(int id) {
		return courseRepository.findById(id).get();
	}
	public void addCourse(Courses course) {
		courseRepository.save(course);
		
	}
	public void updateCourse(Courses course) {
		courseRepository.save(course);
		
	}
	public void deleteCourse(Integer id) {
		courseRepository.deleteById(id);
		
	}
}
