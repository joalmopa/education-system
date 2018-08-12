package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.GroupEntity;
import co.com.education.database.repository.GroupRepository;
import co.com.education.domain.entity.Group;
import co.com.education.domain.gateway.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GroupGatewayImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public List<Group> getGroups() {
        return groupRepository.findAll().stream()
                .map(this::toCore)
                .collect(Collectors.toList());
    }

    @Override
    public Group getGroupById(Integer groupId) {
        return toCore(groupRepository.findOne(groupId));
    }

    @Override
    public Group saveOrUpdateGroup(Group group) {
        return toCore(groupRepository.save(toEntity(group)));
    }

    @Override
    public void deleteGroup(Integer groupId) {
        groupRepository.delete(groupId);
    }

    public Group toCore(GroupEntity entity) {
        return Group.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .openYear(entity.getOpenYear())
                .build();
    }

    public GroupEntity toEntity(Group core) {
        return GroupEntity.builder()
                .id(core.getId())
                .description(core.getDescription())
                .openYear(core.getOpenYear())
                .build();
    }
}