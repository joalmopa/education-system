package co.com.education.database.gatewayimpl;

import co.com.education.config.H2JpaConfig;
import co.com.education.database.entity.SubjectEntity;
import co.com.education.database.repository.SubjectRepository;
import co.com.education.domain.entity.Subject;
import org.hamcrest.core.Is;
import org.junit.After;
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

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { AnnotationConfigContextLoader.class, H2JpaConfig.class })
@TestPropertySource("classpath:/application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectGatewayImplTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectGatewayImpl subjectGatewayImpl;


    @Before
    public void setUp() throws Exception {
        subjectRepository.deleteAll();
    }


    @Test
    public void shouldGetASubject() throws Exception {

        SubjectEntity subjectEntity = getSubjectEntity(11, "Subject 123");
        Subject subject = subjectGatewayImpl.toCore(subjectEntity);

        assertThat(subjectEntity.getId(), Is.is(subject.getId()));
        assertThat(subjectEntity.getDescription(), Is.is(subject.getDescription()));
    }

    @Test
    public void shouldGetASubjectEntity() throws Exception {

        Subject subject = getSubject(8, "Subject 801");
        SubjectEntity subjectEntity = subjectGatewayImpl.toEntity(subject);

        assertThat(subjectEntity.getId(), Is.is(subject.getId()));
        assertThat(subjectEntity.getDescription(), Is.is(subject.getDescription()));
    }

    @Test
    public void shouldGetAllSubjects() throws Exception {

        SubjectEntity subjectEntityOne = subjectRepository.save(getSubjectEntity(1, "Subject One"));
        SubjectEntity subjectEntityTwo = subjectRepository.save(getSubjectEntity(2, "Subject Two"));
        SubjectEntity subjectEntityThree = subjectRepository.save(getSubjectEntity(3, "Subject Three"));

        List<Subject> subjects = subjectGatewayImpl.getSubjects();

        assertThat(subjects.size(), Is.is(3));
        assertThat(subjects.get(0).getId(), Is.is(1));
        assertThat(subjects.get(0).getDescription(), Is.is("Subject One"));
        assertThat(subjects.get(2).getId(), Is.is(3));
        assertThat(subjects.get(2).getDescription(), Is.is("Subject Three"));
        assertThat(subjects.get(1).getId(), Is.is(2));
        assertThat(subjects.get(1).getDescription(), Is.is("Subject Two"));
    }

    @Test
    public void shouldGetOneSubjectById() throws Exception {

        SubjectEntity subjectEntityOne = subjectRepository.save(getSubjectEntity(4, "Subject One"));
        SubjectEntity subjectEntityTwo = subjectRepository.save(getSubjectEntity(5, "Subject Two"));
        SubjectEntity subjectEntityThree = subjectRepository.save(getSubjectEntity(6, "Subject Three"));

        Subject subject = subjectGatewayImpl.getSubjectById(subjectEntityTwo.getId());

        assertThat(subject.getId(), Is.is(5));
        assertThat(subject.getDescription(), Is.is("Subject Two"));
    }

    @Test
    public void shouldRemovedASubject() throws Exception {

        SubjectEntity subjectEntityOne = subjectRepository.save(getSubjectEntity(7, "Subject One"));
        SubjectEntity subjectEntityTwo = subjectRepository.save(getSubjectEntity(8, "Subject Two"));
        SubjectEntity subjectEntityThree = subjectRepository.save(getSubjectEntity(9, "Subject Three"));

        subjectGatewayImpl.deleteSubject(subjectEntityOne.getId());
        List<Subject> subjects = subjectGatewayImpl.getSubjects();

        assertThat(subjects.size(), Is.is(2));
        assertThat(subjects.get(0).getId(), Is.is(8));
        assertThat(subjects.get(0).getDescription(), Is.is("Subject Two"));
        assertThat(subjects.get(1).getId(), Is.is(9));
        assertThat(subjects.get(1).getDescription(), Is.is("Subject Three"));
    }

    @Test
    public void shouldSaveASubject() throws Exception {

        Subject subject = getSubject(10, "Subject 601");
        Subject subjectSaved = subjectGatewayImpl.saveOrUpdateSubject(subject);
        List<Subject> subjects = subjectGatewayImpl.getSubjects();

        assertThat(subjects.size(), Is.is(1));
        assertThat(subjectSaved.getId(), Is.is(10));
        assertThat(subjectSaved.getDescription(), Is.is("Subject 601"));
    }

    @Test
    public void shouldUpdateASubject() throws Exception {

        SubjectEntity subjectEntityOne = subjectRepository.save(getSubjectEntity(11, "Subject One"));
        SubjectEntity subjectEntityTwo = subjectRepository.save(getSubjectEntity(12, "Subject Two"));
        SubjectEntity subjectEntityThree = subjectRepository.save(getSubjectEntity(13, "Subject Three"));
        Subject subject = getSubject(13, "Subject Three Update");

        Subject subjectUpdated = subjectGatewayImpl.saveOrUpdateSubject(subject);
        List<Subject> subjects = subjectGatewayImpl.getSubjects();

        assertThat(subjects.size(), Is.is(3));
        assertThat(subjectUpdated.getId(), Is.is(13));
        assertThat(subjectUpdated.getDescription(), Is.is("Subject Three Update"));
    }


    private SubjectEntity getSubjectEntity(int id, String description) {
        return SubjectEntity.builder()
                .id(id)
                .description(description)
                .build();
    }

    private Subject getSubject(int id, String description) {
        return Subject.builder()
                .id(id)
                .description(description)
                .build();
    }

    @After
    public void setUpAfter() throws Exception {
        subjectRepository.deleteAll();
    }
}