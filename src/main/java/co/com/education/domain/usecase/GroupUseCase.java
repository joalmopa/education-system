package co.com.education.domain.usecase;


import co.com.education.domain.entity.Group;
import co.com.education.domain.gateway.GroupService;

import java.util.List;


public class GroupUseCase {

    private GroupService groupService;

    public GroupUseCase(GroupService groupService) {
        this.groupService = groupService;
    }


    public List<Group> findGroups() {
        return groupService.getGroups();
    }

    public Group findGroupById(Integer groupId) {
        return groupService.getGroupById(groupId);
    }

    public Group saveOrUpdateGroup(Group group) {
        return groupService.saveOrUpdateGroup(group);
    }

    public void deleteGroup(Integer groupId) {
        groupService.deleteGroup(groupId);
    }
}