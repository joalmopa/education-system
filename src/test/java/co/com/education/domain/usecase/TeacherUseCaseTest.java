package co.com.education.domain.usecase;

import co.com.education.domain.entity.Teacher;
import co.com.education.domain.gateway.TeacherService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TeacherUseCaseTest {

    private TeacherService teacherService;
    private TeacherUseCase teacherUseCase;
    private  static final int ID_TEACHER = 1;

    @Before
    public void setUp() throws Exception {

        teacherService = mock(TeacherService.class);
        teacherUseCase =  new TeacherUseCase(teacherService);
    }

    @Test
    public void shouldGetAllTeachers(){

        Mockito.when(teacherService.getTeachers()).thenReturn(getAllTeachers());
        List<Teacher> teachers = teacherUseCase.findTeachers();
        Mockito.verify(teacherService).getTeachers();

        Assert.assertNotNull(teachers);
        Assertions.assertThat(teachers.size()).isGreaterThan(0);
        Assertions.assertThat(teachers.get(3).getDocumentNumber()).isEqualTo("124");
    }

    @Test
    public void shouldGetTeacherById(){
        Teacher teacher = createTeacher(ID_TEACHER,"dd","1234", "M");
        Mockito.when(teacherService.getTeacherById(ID_TEACHER)).thenReturn(teacher);

        Teacher teacherReturn = teacherUseCase.findTeacherById(ID_TEACHER);
        Mockito.verify(teacherService).getTeacherById(ID_TEACHER);
        Assertions.assertThat(teacherReturn.getDocumentNumber()).isEqualTo("1234");

    }

    public List<Teacher> getAllTeachers(){
        return Arrays.asList(
                createTeacher(1,"dd","1234", "M"),
                createTeacher(2,"aa","12", "F"),
                createTeacher(3,"bb","134", "F"),
                createTeacher(4,"cc","124", "M")
        );
    }
    public Teacher createTeacher(Integer id,String name, String documentNumber,String gender){
        return  Teacher.builder()
                .id(id)
                .name(name)
                .documentNumber(documentNumber)
                .gender(gender)
                .birthDate(LocalDate.now())
                .build();
    }
}
