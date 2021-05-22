package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping("topic/{topicId}/courses")
	public List<Course> getAllCourses(@PathVariable String topicId)
	{
		//System.out.println("in get");
		return courseService.getAllCourses(topicId);		
	}
	
	@RequestMapping("/topic/{id}/courses/{courseId}")
	public Optional<Course> getCourseById(@PathVariable("id") String id, @PathVariable String courseId) {
		return courseService.getCourseById(courseId);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course,@PathVariable String topicId)
	{
		course.setTopic(new Topic("",topicId,""));
		courseService.addCourse(course);
	}
	
	
	@RequestMapping(method= RequestMethod.PUT, value="/topics/{topicId}/courses/{courseId}")
	public void updateCourse(@RequestBody Course course, @PathVariable String courseId, @PathVariable String topicId )
	{
		//System.out.println("i am here");
		course.setTopic(new Topic("",topicId,""));	
		courseService.updateCourse(course, courseId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}")
	public void deleteCourse(@PathVariable String courseId)
	{
		courseService.deleteCourse(courseId);

	}
	
}
