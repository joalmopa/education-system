package co.com.education.domain.usecase;

import co.com.education.domain.entity.Student;
import co.com.education.domain.gateway.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class StudentUseCaseTest {

    private static final  int ID_STUDENT=12;

    private StudentService studentService;
    private StudentUseCase studentUseCase;



    @Before
    public void setUp() throws Exception {

        studentService = mock(StudentService.class);
        studentUseCase =  new StudentUseCase(studentService);
    }



    @Test
    public void shouldGetAllStudents()  {

        Mockito.when(studentService.getStudents()).thenReturn(getAllStudent());

        List<Student> students=studentUseCase.findStudents();

        Mockito.verify(studentService).getStudents();

        Assert.assertNotNull(students);
        Assertions.assertThat(students.size()).isGreaterThan(0);
        Assertions.assertThat(students.get(2).getId()).isEqualTo(3);

    }

    @Test
    public void shouldGetStudentById() throws Exception {
        Student student = getStudent(ID_STUDENT,"cc","3232","jovan", "323","", 10l);
        Mockito.when(studentService.getStudentById(ID_STUDENT)).thenReturn(student);

        Student studentReturn=studentUseCase.findStudentById(ID_STUDENT);

        Mockito.verify(studentService).getStudentById(ID_STUDENT);
        Assertions.assertThat(studentReturn.getId()).isEqualTo(ID_STUDENT);

    }

    @Test
    public void shouldSaveStudent() throws Exception {

        Student student = getStudent(ID_STUDENT,"cc","3232","jovan", "323","", 10l);
        Mockito.when(studentService.saveOrUpdateStudent(student)).thenReturn(student);

        Student studentReturn=studentUseCase.saveOrUpdateStudent(student);

        Mockito.verify(studentService).saveOrUpdateStudent(student);
        Assertions.assertThat(studentReturn.getDocumentNumber()).isEqualTo(student.getDocumentNumber());


    }

    @Test
    public void deleteStudent() throws Exception {
    }


    private List<Student> getAllStudent(){

        return Arrays.asList(
                getStudent(1,"cc","3232","jovan", "323","", 10l),
                getStudent(2,"cc","2222","jovan 2", "323","", 20l),
                getStudent(3,"cc","3333","jovan 3", "323","", 30l)
        );
    }

    private Student getStudent( Integer id,String documentType,String documentNumber,String name,String phone,String email,long years){

        return Student.builder().id(id)
                .documentType(documentType)
                .documentNumber(documentNumber)
                .documentType(documentType)
                .name(name)
                .phone(phone)
                .email(email)
                .birthDate(LocalDate.now().minusYears(years))
                .build();
    }



}