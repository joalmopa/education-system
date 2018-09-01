package co.com.education.database.gatewayimpl;

import co.com.education.config.H2JpaConfig;
import co.com.education.database.entity.StudentEntity;
import co.com.education.database.repository.StudentRepository;
import co.com.education.domain.entity.Student;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.junit.Assert.assertThat;
import org.hamcrest.core.Is;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { AnnotationConfigContextLoader.class, H2JpaConfig.class })
@TestPropertySource("classpath:/application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentGatewayImplTest {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired

    private StudentGatewayImpl studentGatewayImpl;


    @Before
    public void setUp() throws Exception {
        studentRepository.deleteAll();
    }


    @Test
    public void shouldGetAStudent() throws Exception {

        StudentEntity studentEntity = getStudentEntity(11, "jose","123", "M");

        Student student = studentGatewayImpl.toCore(studentEntity);


        assertThat(studentEntity.getId(), Is.is(student.getId()));
        assertThat(studentEntity.getName(), Is.is(student.getName()));
    }
    @Test
    public void shouldGetAllStudents() throws Exception {

        StudentEntity studentEntityOne = studentRepository.save(getStudentEntity(1, "jose","123", "M"));
        StudentEntity studentEntityTwo = studentRepository.save(getStudentEntity(2, "david","1234", "M"));
        StudentEntity  studentEntityThree = studentRepository.save(getStudentEntity(3, "pedro","12345", "M"));


        List<Student> students = studentGatewayImpl.getStudents();


        assertThat(students.size(), Is.is(3));
        assertThat(students.get(0).getId(), Is.is(4));
        assertThat(students.get(0).getName(), Is.is("jose"));
        assertThat(students.get(2).getId(), Is.is(6));
        assertThat(students.get(2).getGender(), Is.is("M"));
        assertThat(students.get(1).getId(), Is.is(5));
        assertThat(students.get(1).getDocumentNumber(), Is.is("1234"));
    }

    @Test
    public void shouldGetAStudentById() throws Exception {

        StudentEntity studentEntityOne = studentRepository.save(getStudentEntity(4, "jose","123", "M"));
        StudentEntity studentEntityTwo = studentRepository.save(getStudentEntity(5, "david","1234", "M"));
        StudentEntity  studentEntityThree = studentRepository.save(getStudentEntity(6, "pedro","12345", "M"));


        Student student = studentGatewayImpl.getStudentById(studentEntityThree.getId());


        assertThat(student.getId(), Is.is(3));
        assertThat(student.getName(), Is.is("pedro"));
    }

    @Test
    public void shouldUpdateACourse() throws Exception {

        StudentEntity studentEntityOne = studentRepository.save(getStudentEntity(7, "jose","123", "M"));
        StudentEntity studentEntityTwo = studentRepository.save(getStudentEntity(8, "david","1234", "M"));
        StudentEntity  studentEntityThree = studentRepository.save(getStudentEntity(9, "pedro","12345", "M"));

        Student student = getStudent(7, "jose marco","123", "M");


        Student studentUpdate = studentGatewayImpl.saveOrUpdateStudent(student);
        List<Student> students = studentGatewayImpl.getStudents();


        assertThat(students.size(), Is.is(3));
        assertThat(studentUpdate.getId(), Is.is(7));
        assertThat(studentUpdate.getName(), Is.is("jose marco"));
    }


    private StudentEntity getStudentEntity(int id, String nombre, String identificacion, String genero ){
        return StudentEntity.builder()
                .id(id)
                .name(nombre)
                .documentNumber(identificacion)
                .gender(genero)
                .build();
    }

    private Student getStudent(int id, String nombre, String identificacion, String genero ){
        return Student.builder()
                .id(id)
                .name(nombre)
                .documentNumber(identificacion)
                .gender(genero)
                .build();

    }
}
