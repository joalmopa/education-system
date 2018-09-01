package co.com.education.database.gatewayimpl;

import co.com.education.config.H2JpaConfig;
import co.com.education.database.entity.GroupEntity;
import co.com.education.database.repository.GroupRepository;
import co.com.education.domain.entity.Group;
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

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { AnnotationConfigContextLoader.class, H2JpaConfig.class })
@TestPropertySource("classpath:/application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupGatewayImplTest {


    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupGatewayImpl groupGatewayImpl;


    @Before
    public void setUp() throws Exception {
        groupRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        groupRepository.deleteAll();
    }


    @Test
    public void shoulConvertFromEntityToCore() throws Exception {
        GroupEntity groupEntity = getGroupEntity(2, "Group 2", "2010");

        Group group = groupGatewayImpl.toCore(groupEntity);

        assertThat(groupEntity.getId(), Is.is(group.getId()));
        assertThat(groupEntity.getDescription(), Is.is(group.getDescription()));
        assertThat(groupEntity.getOpenYear(), Is.is(group.getOpenYear()));
    }

    @Test
    public void shoulConvertFromCoreToEntity() throws Exception {
        Group group = getGroup(1, "Group 1", "2008");

        GroupEntity groupEntity = groupGatewayImpl.toEntity(group);

        assertThat(group.getId(), Is.is(groupEntity.getId()));
        assertThat(group.getDescription(), Is.is(groupEntity.getDescription()));
        assertThat(group.getOpenYear(), Is.is(groupEntity.getOpenYear()));
    }


    @Test
    public void shouldGetAllGroups() throws Exception {

        GroupEntity groupEntityOne = groupRepository.save(getGroupEntity(8, "Group 1","2012"));
        GroupEntity groupEntityTwo = groupRepository.save(getGroupEntity(9, "Group 2","2015"));
        GroupEntity groupEntityThree = groupRepository.save(getGroupEntity(10, "Group 3","2010"));

        List<Group> groups = groupGatewayImpl.getGroups();

        assertThat(groups.size(), Is.is(3));
        assertThat(groups.get(0).getId(), Is.is(8));
        assertThat(groups.get(0).getDescription(), Is.is("Group 1"));
        assertThat(groups.get(0).getOpenYear(), Is.is("2012"));
        assertThat(groups.get(1).getId(), Is.is(9));
        assertThat(groups.get(1).getDescription(), Is.is("Group 2"));
        assertThat(groups.get(1).getOpenYear(), Is.is("2015"));
        assertThat(groups.get(2).getId(), Is.is(10));
        assertThat(groups.get(2).getDescription(), Is.is("Group 3"));
        assertThat(groups.get(2).getOpenYear(), Is.is("2010"));
    }


    @Test
    public void shouldGetAGroupById() throws Exception {

        GroupEntity groupEntityFour = groupRepository.save(getGroupEntity(5, "Group 4","2012"));
        GroupEntity groupEntityFive = groupRepository.save(getGroupEntity(6, "Group 5","2015"));
        GroupEntity groupEntitySix = groupRepository.save(getGroupEntity(7, "Group 6","2010"));

        Group group = groupGatewayImpl.getGroupById(groupEntityFive.getId());

        assertThat(group, Is.is(notNullValue()));
        assertThat(group.getId(), Is.is(groupEntityFive.getId()));
        assertThat(group.getDescription(), Is.is(groupEntityFive.getDescription()));
        assertThat(group.getOpenYear(), Is.is(groupEntityFive.getOpenYear()));
    }


    @Test
    public void ShouldSaveOrUpdateGroup() throws Exception {
        Group groupToSave = getGroup(4, "Group 4","2020");

        Group groupSaved = groupGatewayImpl.saveOrUpdateGroup(groupToSave);
        List<Group> groups = groupGatewayImpl.getGroups();

        assertThat(groups.size(), Is.is(1));
        assertThat(groups.get(0).getId(), Is.is(groupToSave.getId()));
        assertThat(groupSaved.getDescription(), Is.is(groupToSave.getDescription()));
        assertThat(groups.get(0).getOpenYear(), Is.is(groupToSave.getOpenYear()));
    }


    @Test
    public void ShouldDeleteGroup() throws Exception {

        GroupEntity groupEntityOne = groupRepository.save(getGroupEntity(1, "Group 1","2012"));
        GroupEntity groupEntityTwo = groupRepository.save(getGroupEntity(2, "Group 2","2015"));
        GroupEntity groupEntityThree = groupRepository.save(getGroupEntity(3, "Group 3","2010"));

        groupGatewayImpl.deleteGroup(groupEntityTwo.getId());
        List<Group> groups = groupGatewayImpl.getGroups();

        assertThat(groups.size(), Is.is(2));
        assertThat(groups.get(1).getId(), Is.is(3));
        assertThat(groups.get(1).getDescription(), Is.is("Group 3"));
        assertThat(groups.get(1).getOpenYear(), Is.is("2010"));
    }


    private GroupEntity getGroupEntity(int id, String description, String openYear) {
        return GroupEntity.builder()
                .id(id)
                .description(description)
                .openYear(openYear)
                .build();
    }

    private Group getGroup(int id, String description, String openYear) {
        return Group.builder()
                .id(id)
                .description(description)
                .openYear(openYear)
                .build();
    }
}