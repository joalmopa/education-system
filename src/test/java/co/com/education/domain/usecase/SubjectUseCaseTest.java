package co.com.education.domain.usecase;

import co.com.education.domain.entity.Subject;
import co.com.education.domain.gateway.SubjectService;
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
public class SubjectUseCaseTest {

    private SubjectUseCase subjectUseCase;

    @Mock
    private SubjectService subjectService;


    @Before
    public void setUp() throws Exception {
        this.subjectUseCase = new SubjectUseCase(subjectService);
    }


    @Test
    public void shouldGetAllSubjects() throws Exception {

        List<Subject> subjects = getSubjects();

        when(subjectService.getSubjects()).thenReturn(subjects);
        List<Subject> subjectsResult = subjectUseCase.findSubjects();

        verify(subjectService, times(1)).getSubjects();
        verifyNoMoreInteractions(subjectService);

        Assertions.assertThat(subjectsResult.size()).isEqualTo(subjects.size());
        Assertions.assertThat(subjectsResult.get(0).getId()).isEqualTo(subjects.get(0).getId());
        Assertions.assertThat(subjectsResult.get(2).getDescription()).isEqualTo(subjects.get(2).getDescription());
    }

    @Test
    public void shouldGetASubjectById() throws Exception {

        Subject subject = getSubject(10, "Subject Expected");
        doReturn(subject).when(subjectService).getSubjectById(subject.getId());

        Subject subjectResult = subjectUseCase.findSubjectById(subject.getId());

        verify(subjectService, times(1)).getSubjectById(subject.getId());
        verifyNoMoreInteractions(subjectService);

        Assertions.assertThat(subjectResult.getId()).isEqualTo(subject.getId());
        Assertions.assertThat(subjectResult.getDescription()).isEqualTo(subject.getDescription());
    }

    @Test
    public void shouldSaveASubject() throws Exception {

        Subject subjectToSave = getSubject(0, "Subject Save");
        Subject subjectSaved = getSubject(5, "Subject Save");
        doReturn(subjectSaved).when(subjectService).saveOrUpdateSubject(subjectToSave);

        Subject subjectSavedResult = subjectUseCase.saveOrUpdateSubject(subjectToSave);

        verify(subjectService, times(1)).saveOrUpdateSubject(subjectToSave);
        verifyNoMoreInteractions(subjectService);

        Assertions.assertThat(subjectSavedResult.getId()).isEqualTo(subjectSaved.getId());
        Assertions.assertThat(subjectSavedResult.getDescription()).isEqualTo(subjectSaved.getDescription());
    }

    @Test
    public void shouldUpdateASubject() throws Exception {
        Subject subjectToUpdate = getSubject(0, "Subject Update");
        Subject subjectUpdated = getSubject(8, "Subject Update");
        doReturn(subjectUpdated).when(subjectService).saveOrUpdateSubject(subjectToUpdate);

        Subject subjectUpdatedResult = subjectUseCase.saveOrUpdateSubject(subjectToUpdate);

        verify(subjectService, times(1)).saveOrUpdateSubject(subjectToUpdate);
        verifyNoMoreInteractions(subjectService);

        Assertions.assertThat(subjectUpdatedResult.getId()).isEqualTo(subjectUpdated.getId());
        Assertions.assertThat(subjectUpdatedResult.getDescription()).isEqualTo(subjectUpdated.getDescription());
    }

    @Test
    public void shouldDeleteASubject() throws Exception {

        Subject subjectToRemove = getSubject(12, "Subject To Remove");
        doNothing().when(subjectService).deleteSubject(subjectToRemove.getId());

        subjectUseCase.deleteSubject(subjectToRemove.getId());

        verify(subjectService, times(1)).deleteSubject(subjectToRemove.getId());
        verifyNoMoreInteractions(subjectService);
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
}