package co.com.education.domain.gateway;


import co.com.education.domain.entity.Course;

import java.util.List;


public interface CourseService {

    List<Course> getCourses();

    Course getCourseById(Integer courseId);

    Course saveOrUpdateCourse(Course course);

    void deleteCourse(Integer courseId);
}
