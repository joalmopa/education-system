package co.com.education.database.jpa.repositoryimpl;

import co.com.education.database.jpa.entity.CourseEntity;
import co.com.education.database.jpa.repository.CourseRepository;
import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class CourseRepositoryImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> getCourses() {

        List<CourseEntity> courses = courseRepository.findAll();

        System.out.println("courses size " + courses.size());

        return null;
    }




}
