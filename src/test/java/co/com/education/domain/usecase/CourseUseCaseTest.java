package co.com.education.domain.usecase;

import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseUseCaseTest {

    private CourseUseCase courseUseCase;

    @Mock
    private CourseService courseService;


    @Before
    public void setUp() throws Exception {
        this.courseUseCase = new CourseUseCase(courseService);
    }


    @Test
    public void shouldGetAllCourses() throws Exception {

        List<Course> courses = getCourses();

        when(courseService.getCourses()).thenReturn(courses);
        List<Course> coursesResult = courseUseCase.findCourses();

        verify(courseService, times(1)).getCourses();
        verifyNoMoreInteractions(courseService);

        Assertions.assertThat(coursesResult.size()).isEqualTo(courses.size());
        Assertions.assertThat(coursesResult.get(0).getId()).isEqualTo(courses.get(0).getId());
        Assertions.assertThat(coursesResult.get(2).getDescription()).isEqualTo(courses.get(2).getDescription());
    }

    @Test
    public void shouldGetACourseById() throws Exception {

        Course course = getCourse(10, "Course Expected");
        doReturn(course).when(courseService).getCourseById(course.getId());

        Course courseResult = courseUseCase.findCourseById(course.getId());

        verify(courseService, times(1)).getCourseById(course.getId());
        verifyNoMoreInteractions(courseService);

        Assertions.assertThat(courseResult.getId()).isEqualTo(course.getId());
        Assertions.assertThat(courseResult.getDescription()).isEqualTo(course.getDescription());
    }

    @Test
    public void shouldSaveACourse() throws Exception {

        Course courseToSave = getCourse(0, "Course Save");
        Course courseSaved = getCourse(5, "Course Save");
        doReturn(courseSaved).when(courseService).saveOrUpdateCourse(courseToSave);

        Course courseSavedResult = courseUseCase.saveOrUpdateCourse(courseToSave);

        verify(courseService, times(1)).saveOrUpdateCourse(courseToSave);
        verifyNoMoreInteractions(courseService);

        Assertions.assertThat(courseSavedResult.getId()).isEqualTo(courseSaved.getId());
        Assertions.assertThat(courseSavedResult.getDescription()).isEqualTo(courseSaved.getDescription());
    }

    @Test
    public void shouldUpdateACourse() throws Exception {
        Course courseToUpdate = getCourse(0, "Course Update");
        Course courseUpdated = getCourse(8, "Course Update");
        doReturn(courseUpdated).when(courseService).saveOrUpdateCourse(courseToUpdate);

        Course courseUpdatedResult = courseUseCase.saveOrUpdateCourse(courseToUpdate);

        verify(courseService, times(1)).saveOrUpdateCourse(courseToUpdate);
        verifyNoMoreInteractions(courseService);

        Assertions.assertThat(courseUpdatedResult.getId()).isEqualTo(courseUpdated.getId());
        Assertions.assertThat(courseUpdatedResult.getDescription()).isEqualTo(courseUpdated.getDescription());
    }

    @Test
    public void shouldDeleteACourse() throws Exception {

        Course courseToRemove = getCourse(12, "Course To Remove");
        doNothing().when(courseService).deleteCourse(courseToRemove.getId());

        courseUseCase.deleteCourse(courseToRemove.getId());

        verify(courseService, times(1)).deleteCourse(courseToRemove.getId());
        verifyNoMoreInteractions(courseService);
    }


    private List<Course> getCourses() {
        return Arrays.asList(
                getCourse(1, "Course 1"),
                getCourse(2, "Course 2"),
                getCourse(3, "Course 3"),
                getCourse(4, "Course 4")
        );
    }

    private Course getCourse(int idCourse, String description) {
        return Course.builder()
                .id(idCourse)
                .description(description)
                .build();
    }
}