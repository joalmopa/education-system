package co.com.education.controller;

import co.com.education.domain.entity.Course;
import co.com.education.domain.usecase.CourseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class CourseController {

    @Autowired
    private CourseUseCase courseUseCase;

    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> getCourses() {

        List<Course> courses = courseUseCase.findCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/course/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Integer courseId) {
        Course course = courseUseCase.findCourseById(courseId);
        return new ResponseEntity<>(course, ((course != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @PostMapping(value="/course")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<> (courseUseCase.saveOrUpdateCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") Integer courseId) {
        courseUseCase.deleteCourse(courseId);
        return new ResponseEntity<Course>(HttpStatus.OK);

    }
}
