package co.com.education.domain.usecase;


import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;

import java.util.List;
import java.util.Map;


public class CourseUseCase {

    private CourseService courseService;

    public CourseUseCase(CourseService courseService) {

        this.courseService = courseService;
    }


    public List<Course> findCourses() {
        return courseService.getCourses();
    }



}
