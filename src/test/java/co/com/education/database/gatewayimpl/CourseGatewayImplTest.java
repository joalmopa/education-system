package co.com.education.database.gatewayimpl;

import co.com.education.config.H2JpaConfig;
import co.com.education.database.entity.CourseEntity;
import co.com.education.database.repository.CourseRepository;
import co.com.education.domain.entity.Course;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { AnnotationConfigContextLoader.class, H2JpaConfig.class })
@TestPropertySource("classpath:/application-test.properties")
public class CourseGatewayImplTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseGatewayImpl courseGatewayImpl;

    @Before
    public void setUp() throws Exception {
        courseRepository.deleteAll();
    }


    @Test
    public void shouldGetAllCourses() throws Exception {

        CourseEntity courseEntityOne = courseRepository.save(getCourseEntity(1, "Course One"));
        CourseEntity courseEntityTwo = courseRepository.save(getCourseEntity(2, "Course Two"));
        CourseEntity courseEntityThree = courseRepository.save(getCourseEntity(3, "Course Three"));

        List<Course> courses = courseGatewayImpl.getCourses();

        assertThat(courses.size(), Is.is(3));
        assertThat(courses.get(0).getId(), Is.is(1));
        assertThat(courses.get(0).getDescription(), Is.is("Course One"));
        assertThat(courses.get(2).getId(), Is.is(3));
        assertThat(courses.get(2).getDescription(), Is.is("Course Three"));
        assertThat(courses.get(1).getId(), Is.is(2));
        assertThat(courses.get(1).getDescription(), Is.is("Course Two"));
    }

    @Test
    public void shouldGetOneCourseById() throws Exception {

        CourseEntity courseEntityOne = courseRepository.save(getCourseEntity(4, "Course One"));
        CourseEntity courseEntityTwo = courseRepository.save(getCourseEntity(5, "Course Two"));
        CourseEntity courseEntityThree = courseRepository.save(getCourseEntity(6, "Course Three"));

        Course course = courseGatewayImpl.getCourseById(courseEntityTwo.getId());

        assertThat(course.getId(), Is.is(5));
        assertThat(course.getDescription(), Is.is("Course Two"));
    }

    @Test
    public void shouldSaveACourse() throws Exception {

        Course course = getCourse(6, "Course 601");
        Course courseSaved = courseGatewayImpl.saveOrUpdateCourse(course);
        List<Course> courses = courseGatewayImpl.getCourses();

        assertThat(courses.size(), Is.is(6));
        assertThat(courseSaved.getId(), Is.is(6));
        assertThat(courseSaved.getDescription(), Is.is("Course 601"));
    }

    @Test
    public void shouldUpdateACourse() throws Exception {

        CourseEntity courseEntityOne = courseRepository.save(getCourseEntity(1, "Course One"));
        CourseEntity courseEntityTwo = courseRepository.save(getCourseEntity(2, "Course Two"));
        CourseEntity courseEntityThree = courseRepository.save(getCourseEntity(3, "Course Three"));
        Course course = getCourse(3, "Course Three Update");

        Course courseUpdated = courseGatewayImpl.saveOrUpdateCourse(course);
        List<Course> courses = courseGatewayImpl.getCourses();

        assertThat(courses.size(), Is.is(3));
        assertThat(courseUpdated.getId(), Is.is(3));
        assertThat(courseUpdated.getDescription(), Is.is("Course Three Update"));
    }

    @Test
    public void shouldRemovedACourse() throws Exception {

        CourseEntity courseEntityOne = courseRepository.save(getCourseEntity(1, "Course One"));
        CourseEntity courseEntityTwo = courseRepository.save(getCourseEntity(2, "Course Two"));
        CourseEntity courseEntityThree = courseRepository.save(getCourseEntity(3, "Course Three"));

        courseGatewayImpl.deleteCourse(courseEntityOne.getId());
        List<Course> courses = courseGatewayImpl.getCourses();

        assertThat(courses.size(), Is.is(2));
        assertThat(courses.get(0).getId(), Is.is(2));
        assertThat(courses.get(0).getDescription(), Is.is("Course Two"));
        assertThat(courses.get(1).getId(), Is.is(3));
        assertThat(courses.get(1).getDescription(), Is.is("Course Three"));
    }

    @Test
    public void shouldGetACourseEntity() throws Exception {

        Course course = getCourse(8, "Course 801");
        CourseEntity courseEntity = courseGatewayImpl.toEntity(course);

        assertThat(courseEntity.getId(), Is.is(course.getId()));
        assertThat(courseEntity.getDescription(), Is.is(course.getDescription()));
    }

    @Test
    public void shouldGetACourse() throws Exception {

        CourseEntity courseEntity = getCourseEntity(11, "Course 123");
        Course course = courseGatewayImpl.toCore(courseEntity);

        assertThat(courseEntity.getId(), Is.is(course.getId()));
        assertThat(courseEntity.getDescription(), Is.is(course.getDescription()));
    }

    private CourseEntity getCourseEntity(int id, String description) {
        return CourseEntity.builder()
                .id(id)
                .description(description)
                .build();
    }

    private Course getCourse(int id, String description) {
        return Course.builder()
                .id(id)
                .description(description)
                .build();
    }
}