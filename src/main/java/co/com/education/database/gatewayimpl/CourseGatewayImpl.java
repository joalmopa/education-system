package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.CourseEntity;
import co.com.education.database.repository.CourseRepository;
import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseGatewayImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll().stream()
                .map(this::toCore)
                .collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return toCore(courseRepository.findOne(courseId));
    }

    @Override
    public Course saveOrUpdateCourse(Course course) {
        return toCore(courseRepository.save(toEntity(course)));
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseRepository.delete(courseId);
    }


    public Course toCore(CourseEntity entity) {
        return Course.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .build();
    }

    public CourseEntity toEntity(Course core) {

        return new CourseEntity(core.getId(), core.getDescription());
    }
}
