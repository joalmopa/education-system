package co.com.education.controller;

import co.com.education.domain.entity.Group;
import co.com.education.domain.usecase.GroupUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class GroupController {

    @Autowired
    private GroupUseCase groupUseCase;

    @GetMapping(value = "/groups")
    public ResponseEntity<List<Group>> getGroups() {

        List<Group> groups = groupUseCase.findGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping(value = "/group/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable("groupId") Integer groupId) {
        return new ResponseEntity<>(groupUseCase.findGroupById(groupId), HttpStatus.OK);
    }

    @PostMapping(value="/group")
    public ResponseEntity<Group> save(@RequestBody Group group) {
        return new ResponseEntity<> (groupUseCase.saveOrUpdateGroup(group), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/group/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") Integer groupId) {
        groupUseCase.deleteGroup(groupId);
        return new ResponseEntity<Group>(HttpStatus.NO_CONTENT);
    }
}
