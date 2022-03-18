package casemodules4.controller;

import casemodules4.model.*;
import casemodules4.repository.IGroupRepository;
import casemodules4.service.IGroupMembersService;
import casemodules4.service.IGroupService;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {
    @Value("${upload.path}")
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

    @Autowired
    private IGroupRepository iGroupRepository;

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
    @PostMapping("/create-group/{idUser}")
    public ResponseEntity<Group> createGroup(@PathVariable("idUser") Long idUser, @RequestBody Group group) {
        Group groupCreate = groupService.save(group);
        Group groupByName = iGroupRepository.findByName(group.getName());
        User user = userService.findById(idUser);
        EmbeddedGroupMembers embeddedGroupMembers = new EmbeddedGroupMembers(groupByName.getIdGroup(), idUser);
        GroupMembers groupMembers = new GroupMembers();
        groupMembers.setId(embeddedGroupMembers);
        groupMembers.setGroup(groupByName);
        groupMembers.setUser(user);
        groupMembers.setRole("ROLE_GROUP_ADMIN");
        groupMembersService.save(groupMembers);
        return new ResponseEntity<>(groupCreate, HttpStatus.CREATED);
    }

    //create post in group + upload file
    @PostMapping("/create-post/{idGroup}/{idUser}")
    public ResponseEntity<Post> createPost(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser,
                                           @RequestPart Post post, @RequestPart("file") MultipartFile file) {
        post.setDateCreated(LocalDate.now());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        post.setGroups((Set<Group>) groupService.findById(idGroup).get());
        post.setUserPost(userService.findById(idUser));
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    //search group
    @GetMapping("/search-group/{search}")
    public ResponseEntity<Iterable<Group>> findAllByName(@PathVariable String search) {
        Iterable<Group> groupsList = groupService.findAllByName(search);
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }
}
