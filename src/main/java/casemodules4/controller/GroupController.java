package casemodules4.controller;

import casemodules4.model.*;
import casemodules4.security.jwt.response.ResponseMessage;
import casemodules4.service.IGroupService;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import casemodules4.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping("/{idUser}/list-group")
    public ResponseEntity<List<Group>> showAllGroup(@PathVariable("idUser") Long idUser) {
        List<Group> groups = groupService.findAllByIdUser(idUser);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/{idUser}/join")
    public ResponseEntity<String> joinGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser) {
        groupService.addToGroup(idGroup, idUser);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/{idUser}/leave")
    public ResponseEntity<String> leaveGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser) {
        groupService.leaveToGroup(idGroup, idUser);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{idUser}/groupNoneJoin")
    public ResponseEntity<List<Group>> getGroupNoneJoin(@PathVariable("idUser") Long idUser) {
        List<Group> groupsNoneJoin = groupService.getAll();

        List<Group> groupsJoin = groupService.findAllByIdUser(idUser);

        groupsNoneJoin.removeAll(groupsJoin);

        return new ResponseEntity<>(groupsNoneJoin, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}")
    public ResponseEntity<Group> getGroupByIdGroup(@PathVariable("idGroup") Long idGroup) {
        Group group = groupService.findById(idGroup);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/totalMembers")
    public ResponseEntity<Integer> getTotalMembers(@PathVariable("idGroup") Long idGroup) {
        Integer totalMembers = groupService.getTotalMembers(idGroup);

        return new ResponseEntity<>(totalMembers, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/member")
    public ResponseEntity<List<User>> getMemberInGroup(@PathVariable("idGroup") Long idGroup) {
        List<GroupMembers> groupMembers = groupService.findAllByIdGroup(idGroup);
        List<User> members = new ArrayList<>();
        for (GroupMembers gr: groupMembers) {
           members.add(gr.getUser());
        }

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/notMember")
    public ResponseEntity<List<User>> getMemberNotInGroup(@PathVariable("idGroup") Long idGroup) {
        List<GroupMembers> groupMembers = groupService.findAllByIdGroup(idGroup);
        List<User> members = new ArrayList<>();
        for (GroupMembers gr: groupMembers) {
            members.add(gr.getUser());
        }
        List<User> membersNonInGroup = userService.findAll();
        membersNonInGroup.removeAll(members);
        return new ResponseEntity<>(membersNonInGroup, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/posts")
    public ResponseEntity<List<Post>> getPostInGroup(@PathVariable("idGroup") Long idGroup){
        List<Long> idPosts = groupService.getPostInGroup(idGroup);
        List<Post> posts = new ArrayList<>();
        for (Long idPost: idPosts) {
            posts.add(postService.findById(idPost));
        }

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

//    @GetMapping("/id")

}
