package casemodules4.controller;

import casemodules4.model.Group;
import casemodules4.model.GroupMembers;
import casemodules4.model.User;
import casemodules4.service.IGroupMembersService;
import casemodules4.service.IGroupService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/group-members")
public class GroupMembersController {
    @Autowired
    private IGroupMembersService groupMembersService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @GetMapping
    public ResponseEntity<Iterable<GroupMembers>> getAllGroupMember() {
        Iterable<GroupMembers> groupsMembers = groupMembersService.findAll();
        if (!groupsMembers.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupsMembers, HttpStatus.OK);
    }

    @PostMapping("/send-request/{id_user}/{id_group}")
    public ResponseEntity<GroupMembers> sendRequest(@PathVariable("id_user") Long id_user,
                                                   @PathVariable("id_group") Long id_group) {
        Optional<User> user = Optional.ofNullable(userService.findById(id_user));
        Optional<Group> group = groupService.findById(id_group);
        GroupMembers groupMembers = new GroupMembers(2, user.get(), group.get());
        return new ResponseEntity<>(groupMembersService.save(groupMembers), HttpStatus.CREATED);
    }

    @PutMapping("/join-group/{id_user}/{id_group}")
    public ResponseEntity<GroupMembers> joinGroup(@PathVariable("id_user") Long id_user,
                                                 @PathVariable("id_group") Long id_group) {
        Optional<GroupMembers> groupMembers = groupMembersService.findById_GroupAndId_User(id_group, id_user);
        groupMembers.get().setStatus(1);
        groupMembersService.save(groupMembers.get());
        return new ResponseEntity<>(groupMembers.get(), HttpStatus.OK);
    }

    @DeleteMapping("/out-group/{id_user}/{id_group}")
    public ResponseEntity<GroupMembers> outGroup(@PathVariable("id_user") Long id_user,
                                                @PathVariable("id_group") Long id_group) {
        Optional<GroupMembers> groupMembers = groupMembersService.findById_GroupAndId_User(id_group, id_user);
        groupMembersService.remove(groupMembers.get().getId());
        return new ResponseEntity<>(groupMembers.get(), HttpStatus.OK);
    }
}
