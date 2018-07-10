package co.com.education.controller;

import co.com.education.domain.entity.Course;
import co.com.education.domain.gateway.CourseService;
import co.com.education.domain.usecase.CourseUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
@ContextConfiguration(classes = {CourseController.class })
@WebAppConfiguration
public class CourseControllerTest {

    @MockBean
    private CourseUseCase courseUseCase;

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() {
    }


    @Test
    public void shouldGetAllCourses() throws Exception {

        doReturn(getCourses()).when(courseUseCase).findCourses();

        mockMvc.perform(get("/education/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Course 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Course 2")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].description", is("Course 4")));

        verify(courseUseCase, times(1)).findCourses();
        verifyNoMoreInteractions(courseUseCase);
    }

    @Test
    public void shouldGetACourseById() throws Exception {

        Course course = getCourse(10, "Course Expected");
        doReturn(course).when(courseUseCase).findCourseById(course.getId());

        mockMvc.perform(get("/education/course/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.description", is("Course Expected")));

        verify(courseUseCase, times(1)).findCourseById(course.getId());
        verifyNoMoreInteractions(courseUseCase);
    }

    @Test
    public void shouldGetErrorCode404WhenCourseHasNotFound() throws Exception {

        Course course = getCourse(1, "Course Expected");
        doReturn(null).when(courseUseCase).findCourseById(course.getId());

        mockMvc.perform(get("/education/course/{id}", course.getId()))
                .andExpect(status().isNotFound());

        verify(courseUseCase, times(1)).findCourseById(1);
        verifyNoMoreInteractions(courseUseCase);
    }

    @Test
    public void shouldSaveACourse() throws Exception {

        Course courseSaved = getCourse(7, "Course Saved");
        doReturn(courseSaved).when(courseUseCase).saveOrUpdateCourse(any(Course.class));

        mockMvc.perform(post("/education/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(courseSaved)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(courseSaved.getId())))
                .andExpect(jsonPath("$.description", is(courseSaved.getDescription())));

        verify(courseUseCase, times(1)).saveOrUpdateCourse(any(Course.class));
        verifyNoMoreInteractions(courseUseCase);
    }

    @Test
    public void shouldDeleteACourse() throws Exception {

        Course courseToRemove = getCourse(3, "Course to Remove");

        doNothing().when(courseUseCase).deleteCourse(courseToRemove.getId());

        mockMvc.perform(
                delete("/education/course/{id}", courseToRemove.getId()))
                .andExpect(status().isOk());

        verify(courseUseCase, times(1)).deleteCourse(courseToRemove.getId());
        verifyNoMoreInteractions(courseUseCase);
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}