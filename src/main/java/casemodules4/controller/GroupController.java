package casemodules4.controller;

import casemodules4.model.Group;
import casemodules4.model.GroupMembers;
import casemodules4.model.Post;
import casemodules4.model.User;
import casemodules4.service.IGroupMembersService;
import casemodules4.service.IGroupService;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Controller
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupMembersService groupMembersService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping("/get-group-member")
    public ResponseEntity<Iterable<GroupMembers>> getAllGroupMember() {
        Iterable<GroupMembers> groupMembers = groupMembersService.findAll();
        if (!groupMembers.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

    @GetMapping("/get-group")
    public ResponseEntity<Iterable<Group>> getAllGroups() {
        Iterable<Group> groupsList = groupService.findAll();
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }

    //create group
    @PostMapping("/create-group/{id_user}")
    public ResponseEntity<Group> createGroup(@PathVariable("id_user") Long id_user, @RequestBody Group group) {
        Group groupCreate = groupService.save(group);
        User user = userService.findById(id_user);
        GroupMembers groupMembers = new GroupMembers(0, user, group);
        groupMembersService.save(groupMembers);
        return new ResponseEntity<>(groupCreate, HttpStatus.CREATED);
    }

    //create post in group + upload file
    @PostMapping("/create-post/{id_group}/{id_user}")
    public ResponseEntity<Post> createPost(@PathVariable("id_group") Long id_group, @PathVariable("id_user") Long id_user,
                                           @RequestPart Post post, @RequestPart("file") MultipartFile file) {
        post.setDateCreated(LocalDate.now());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        post.setGroup((Set<Group>) groupService.findById(id_group).get());
        post.setUser(userService.findById(id_user));
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    //search group
    @GetMapping("/search-group")
    public ResponseEntity<Iterable<Group>> findAllByName(@RequestParam Optional<String> search) {
        Iterable<Group> groupsList = groupService.findAllByName(search.get());
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }
}
