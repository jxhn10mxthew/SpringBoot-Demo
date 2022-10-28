package com.example.jetbrains.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.jetbrains.topics.Topics;

@RestController
public class CoursesController {
	
	@Autowired
	private CoursesService courseService;
	@RequestMapping("/topics/{id}/courses")
	public List<Courses> getallCourses(@PathVariable Integer id) {
		return courseService.getallCourses(id);
	}
	
	@GetMapping("/topics/{topicId}/courses/{id}")
	public Courses getCourse(@PathVariable Integer id) {
		return courseService.getCourse(id);
	}
	@PostMapping(value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Courses course,@PathVariable Integer topicId) {
		course.setTopic(new Topics(topicId,"",""));
		courseService.addCourse(course);
	}
	@RequestMapping(method = RequestMethod.PUT , value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Courses course,@PathVariable Integer id,@PathVariable Integer topicId) {
		course.setId(id);
		course.setTopic(new Topics(topicId,"",""));
		courseService.updateCourse(course);
	}
	@RequestMapping(method = RequestMethod.DELETE , value="/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable Integer id) {
		courseService.deleteCourse(id);
	}
}
