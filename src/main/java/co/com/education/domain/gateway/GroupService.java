package co.com.education.domain.gateway;


import co.com.education.domain.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getGroups();

    Group getGroupById(Integer groupId);

    Group saveOrUpdateGroup(Group group);

    void deleteGroup(Integer groupId);

}
