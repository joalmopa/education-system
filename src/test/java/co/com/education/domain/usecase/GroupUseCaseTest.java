package co.com.education.domain.usecase;

import co.com.education.domain.entity.Group;
import co.com.education.domain.gateway.GroupService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupUseCaseTest {


    private GroupUseCase groupUseCase;

    @Mock
    private GroupService groupService;


    @Before
    public void setUp() throws Exception {
        this.groupUseCase = new GroupUseCase(groupService);
    }



    @Test
    public void shouldGetAllGroups() throws Exception {

        List<Group> groups = getGroups();

        when(groupService.getGroups()).thenReturn(groups);
        List<Group> groupsResult = groupUseCase.findGroups();

        verify(groupService, times(1)).getGroups();
        verifyNoMoreInteractions(groupService);

        Assertions.assertThat(groupsResult.size()).isEqualTo(groups.size());
        Assertions.assertThat(groupsResult.get(0).getId()).isEqualTo(groups.get(0).getId());
        Assertions.assertThat(groupsResult.get(2).getDescription()).isEqualTo(groups.get(2).getDescription());
    }


    @Test
    public void shouldGetAGroupById() throws Exception {

        Group group = getGroup(10, "Group 1", "2014");
        doReturn(group).when(groupService).getGroupById(group.getId());

        Group subjectResult = groupUseCase.findGroupById(group.getId());

        verify(groupService, times(1)).getGroupById(group.getId());
        verifyNoMoreInteractions(groupService);

        Assertions.assertThat(subjectResult.getId()).isEqualTo(group.getId());
        Assertions.assertThat(subjectResult.getDescription()).isEqualTo(group.getDescription());
    }


    @Test
    public void shouldSaveAGroup() throws Exception {

    }


    @Test
    public void shouldUpdateAGroup() throws Exception {

    }


    @Test
    public void shoulddeleteGroup() throws Exception {
    }


    private List<Group> getGroups() {
        return Arrays.asList(
                getGroup(1, "Group 1", "2010"),
                getGroup(2, "Group 2", "2015"),
                getGroup(3, "Group 3", "2016"),
                getGroup(4, "Group 4", "2013")
        );
    }

    private Group getGroup(int idGroup, String description, String openYear) {
        return Group.builder()
                .id(idGroup)
                .description(description)
                .openYear(openYear)
                .build();
    }
}