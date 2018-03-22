package co.com.education.domain.usecase;


import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public class CourseUseCase {

    private CourseService courseService;

    public CourseUseCase(CourseService courseService) {
        this.courseService = courseService;
    }


    public List<Course> findCourses() {
        return courseService.getCourses();
    }

    public Course findCourseById(Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    public Course saveOrUpdateCourse(Course course) {
        return courseService.saveOrUpdateCourse(course);
    }

    public void deleteCourse(Integer courseId) {
        courseService.deleteCourse(courseId);
    }
}
