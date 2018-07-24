package co.com.education.database.gatewayimpl;


import co.com.education.config.H2JpaConfig;
import co.com.education.database.entity.TeacherEntity;
import co.com.education.database.repository.TeacherRepository;
import co.com.education.domain.entity.Teacher;
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
public class TeacherGatewayImplTest {


    @Autowired
    TeacherGatewayImpl teacherGatewayImpl;

    @Autowired
    TeacherRepository teacherRepository;

    @Before
    public void setUp() throws Exception {
        teacherRepository.deleteAll();
    }

    @Test
    public void shouldGetAtTeacher() throws Exception {

        TeacherEntity teacherEntity =teacherRepository.save(getTeacherEntity(1, "jose","123", "M")) ;
        Teacher teacher = teacherGatewayImpl.toCore(teacherEntity);

        assertThat(teacherEntity.getId(), Is.is(teacher.getId()));
        assertThat(teacherEntity.getName(), Is.is(teacher.getName()));
    }
    @Test
    public void shouldUpdateACourse() throws Exception {

        TeacherEntity TeacherEntityOne = teacherRepository.save(getTeacherEntity(7, "jose","123", "M"));
        TeacherEntity TeacherEntityTwo = teacherRepository.save(getTeacherEntity(8, "david","1234", "M"));
        TeacherEntity  TeacherEntityThree = teacherRepository.save(getTeacherEntity(9, "pedro","12345", "M"));
        Teacher teacher = getTeacher(7, "jose marco","123", "M");

        Teacher teacherUpdate = teacherGatewayImpl.saveOrUpdateTeacher(teacher);
        List<Teacher> teachers = teacherGatewayImpl.getTeachers();


        assertThat(teachers.size(), Is.is(3));
        assertThat(teacherUpdate.getId(), Is.is(10));
        assertThat(teacherUpdate.getName(), Is.is("jose marco"));
    }


    @Test
    public void shouldGetAllTeachers() throws Exception {


        TeacherEntity TeacherEntityOne = teacherRepository.save(getTeacherEntity(4, "jose","123", "M"));
        TeacherEntity TeacherEntityTwo = teacherRepository.save(getTeacherEntity(5, "david","1234", "M"));
        TeacherEntity  TeacherEntityThree = teacherRepository.save(getTeacherEntity(6, "pedro","12345", "M"));


        List<Teacher> teachers = teacherGatewayImpl.getTeachers();

        assertThat(teachers.size(), Is.is(3));
        assertThat(teachers.get(0).getId(), Is.is(4));
        assertThat(teachers.get(0).getName(), Is.is("jose"));
        assertThat(teachers.get(2).getId(), Is.is(6));
        assertThat(teachers.get(2).getGender(), Is.is("M"));
        assertThat(teachers.get(1).getId(), Is.is(5));
        assertThat(teachers.get(1).getDocumentNumber(), Is.is("1234"));
    }
    @Test
    public void shouldGetATeacherById() throws Exception {

        TeacherEntity TeacherEntityOne = teacherRepository.save(getTeacherEntity(1, "jose","123", "M"));
        TeacherEntity TeacherEntityTwo = teacherRepository.save(getTeacherEntity(2, "david","1234", "M"));
        TeacherEntity  TeacherEntityThree = teacherRepository.save(getTeacherEntity(3, "pedro","12345", "M"));


        Teacher teacher = teacherGatewayImpl.getTeacherById(TeacherEntityThree.getId());

        assertThat(teacher.getId(), Is.is(3));
        assertThat(teacher.getName(), Is.is("pedro"));
    }

    private TeacherEntity getTeacherEntity(int id, String nombre, String identificacion, String genero ){
        return TeacherEntity.builder()
                .id(id)
                .name(nombre)
                .documentNumber(identificacion)
                .gender(genero)
                .build();
    }

    private Teacher getTeacher(int id, String nombre, String identificacion, String genero ){
        return Teacher.builder()
                .id(id)
                .name(nombre)
                .documentNumber(identificacion)
                .gender(genero)
                .build();

    }

}
