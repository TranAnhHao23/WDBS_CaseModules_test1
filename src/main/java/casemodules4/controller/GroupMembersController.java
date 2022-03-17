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

@RestController
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

    @PostMapping("/send-request/{idUser}/{idGroup}")
    public ResponseEntity<GroupMembers> sendRequest(@PathVariable("idUser") Long idUser,
                                                   @PathVariable("idGroup") Long idGroup) {
        Optional<User> user = Optional.ofNullable(userService.findById(idUser));
        Optional<Group> group = groupService.findById(idGroup);
        GroupMembers groupMembers = new GroupMembers(2, user.get(), group.get());
        return new ResponseEntity<>(groupMembersService.save(groupMembers), HttpStatus.CREATED);
    }

    @PutMapping("/join-group/{idUser}/{idGroup}")
    public ResponseEntity<GroupMembers> joinGroup(@PathVariable("idUser") Long idUser,
                                                 @PathVariable("idGroup") Long idGroup) {
        Optional<GroupMembers> groupMembers = groupMembersService.findByIdGroupAndIdUser(idGroup, idUser);
        groupMembers.get().setStatus(1);
        groupMembersService.save(groupMembers.get());
        return new ResponseEntity<>(groupMembers.get(), HttpStatus.OK);
    }

    @DeleteMapping("/out-group/{idUser}/{idGroup}")
    public ResponseEntity<GroupMembers> outGroup(@PathVariable("idUser") Long idUser,
                                                @PathVariable("idGroup") Long idGroup) {
        Optional<GroupMembers> groupMembers = groupMembersService.findByIdGroupAndIdUser(idGroup, idUser);
        groupMembersService.remove(idGroup);
        return new ResponseEntity<>(groupMembers.get(), HttpStatus.OK);
    }
}
