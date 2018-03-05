package co.com.education.domain.usecase;


import co.com.education.domain.gateway.GroupService;


public class GroupUseCase {

    private GroupService groupService;

    public GroupUseCase(GroupService groupService) {
        this.groupService = groupService;
    }
}
