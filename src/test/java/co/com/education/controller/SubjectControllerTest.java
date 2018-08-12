package co.com.education.controller;

import co.com.education.domain.entity.Subject;
import co.com.education.domain.gateway.SubjectService;
import co.com.education.domain.usecase.SubjectUseCase;
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
@WebMvcTest(value = SubjectController.class, secure = false)
@ContextConfiguration(classes = {SubjectController.class })
@WebAppConfiguration
public class SubjectControllerTest {

    @MockBean
    private SubjectUseCase subjectUseCase;

    @MockBean
    private SubjectService subjectService;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() {
    }


    @Test
    public void shouldGetAllSubjects() throws Exception {

        doReturn(getSubjects()).when(subjectUseCase).findSubjects();

        mockMvc.perform(get("/education/subjects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Subject 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Subject 2")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].description", is("Subject 4")));

        verify(subjectUseCase, times(1)).findSubjects();
        verifyNoMoreInteractions(subjectUseCase);
    }

    @Test
    public void shouldGetASubjectById() throws Exception {

        Subject subject = getSubject(10, "Subject Expected");
        doReturn(subject).when(subjectUseCase).findSubjectById(subject.getId());

        mockMvc.perform(get("/education/subject/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.description", is("Subject Expected")));

        verify(subjectUseCase, times(1)).findSubjectById(subject.getId());
        verifyNoMoreInteractions(subjectUseCase);
    }

    @Test
    public void shouldGetErrorCode404WhenSubjectHasNotFound() throws Exception {

        Subject subject = getSubject(1, "Subject Expected");
        doReturn(null).when(subjectUseCase).findSubjectById(subject.getId());

        mockMvc.perform(get("/education/subject/{id}", subject.getId()))
                .andExpect(status().isNotFound());

        verify(subjectUseCase, times(1)).findSubjectById(1);
        verifyNoMoreInteractions(subjectUseCase);
    }

    @Test
    public void shouldSaveASubject() throws Exception {

        Subject subjectSaved = getSubject(7, "Subject Saved");
        doReturn(subjectSaved).when(subjectUseCase).saveOrUpdateSubject(any(Subject.class));

        mockMvc.perform(post("/education/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(subjectSaved)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(subjectSaved.getId())))
                .andExpect(jsonPath("$.description", is(subjectSaved.getDescription())));

        verify(subjectUseCase, times(1)).saveOrUpdateSubject(any(Subject.class));
        verifyNoMoreInteractions(subjectUseCase);
    }

    @Test
    public void shouldDeleteASubject() throws Exception {

        Subject subjectToRemove = getSubject(3, "Subject to Remove");

        doNothing().when(subjectUseCase).deleteSubject(subjectToRemove.getId());

        mockMvc.perform(
                delete("/education/subject/{id}", subjectToRemove.getId()))
                .andExpect(status().isOk());

        verify(subjectUseCase, times(1)).deleteSubject(subjectToRemove.getId());
        verifyNoMoreInteractions(subjectUseCase);
    }

    private List<Subject> getSubjects() {
        return Arrays.asList(
                getSubject(1, "Subject 1"),
                getSubject(2, "Subject 2"),
                getSubject(3, "Subject 3"),
                getSubject(4, "Subject 4")
        );
    }

    private Subject getSubject(int idSubject, String description) {
        return Subject.builder()
                .id(idSubject)
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