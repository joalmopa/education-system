package co.com.education.controller;

import co.com.education.domain.entity.Course;
import co.com.education.domain.entity.Teacher;
import co.com.education.domain.usecase.CourseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("education/")
public class CourseController {

    @Autowired
    private CourseUseCase courseUseCase;

    @GetMapping(value = "courses")
    public ResponseEntity<List<Course>> getCourses() {

        List<Course> courses = courseUseCase.findCourses();

        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

}
