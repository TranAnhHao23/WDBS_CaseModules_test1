package casemodules4.controller;

import casemodules4.model.FriendList;
import casemodules4.model.Post;
import casemodules4.model.User;
import casemodules4.service.IFriendListService;
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
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService postService;


    @Autowired
    private IFriendListService friendListService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Value("${upload.path}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @GetMapping
    public ResponseEntity<List<Post>> showAllPost() {
        List<Post> posts = postService.findAll();
        if (posts.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

//    @GetMapping("/{idPost}")
//    public ResponseEntity<Post> showDetailPost(@PathVariable("idPost") Long id){
//        Post post = postService.findById(id);
//        if (post == null){
//            new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }

    @GetMapping("/{idUser}/newsfeed")
    public ResponseEntity<List<Post>> getAllByIdUser(@PathVariable("idUser") Long id) {
        List<Post> postsPublic = postService.findAllByUserPostIdUserOrStatus(id, "public");
        List<FriendList> friendLists = friendListService.findFriendListByIdUser(id);
        List<User> users = new ArrayList<>();
        for (FriendList friendList : friendLists) {
            if (Objects.equals(friendList.getUserFrom().getIdUser(), id)) {
                users.add(friendList.getUserTo());
            } else {
                users.add(friendList.getUserFrom());
            }
        }
        List<Post> postsFriend = new ArrayList<>();
        for (User user : users) {
            postsFriend.addAll(postService.findAllByUserPostAndStatus(user, "friend"));
        }
        postsPublic.addAll(postsFriend);
        return new ResponseEntity<>(postsPublic, HttpStatus.OK);
    }

    @GetMapping("/{idUserFrom}/{idUserTo}/timeline")
    public ResponseEntity<List<Post>> getPostTimelineByIdUser(@PathVariable("idUserFrom") Long idUserFrom, @PathVariable("idUserTo") Long idUserTo) {
        String status = friendListService.checkFriendsStatus(idUserFrom, idUserTo);
        List<Post> posts = new ArrayList<>();
        if (idUserFrom == idUserTo) {
            posts = postService.findAllByUserPostIdUser(idUserTo);
        } else {
            if (status.equals("friend")) {
                posts = postService.findAllByUserPostIdUser(idUserTo);
            } else if (status.equals("pending") || status.equals("")) {
                User user = userService.findById(idUserTo);
                posts = postService.findAllByUserPostAndStatus(user, "public");
            }
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/newPost")
    public ResponseEntity<Post> createNewPost(@RequestPart("json") Post post, @RequestPart("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        postService.save(post);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/{idGroup}/newPostInGroup")
    public ResponseEntity<Post> createNewPostInGroup(@PathVariable("idGroup") Long idGroup, @RequestPart("json") Post post, @RequestPart("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        Post post1 = postService.save(post);
        groupService.createNewPostInGroup(idGroup, post1.getIdPost());
        return new ResponseEntity<>(post1, HttpStatus.OK);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<Post> updatePost(@PathVariable("idPost") Long id, @RequestBody Post post) {
        Post post1 = postService.findById(id);
        if (post1 == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setIdPost(id);
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<Post> deletePost(@PathVariable("idPost") Long id) {
        Post post = postService.findById(id);
        postService.deleteById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/searchHashtag")
    public ResponseEntity<List<Post>> findAllPostByHashtag(@RequestParam("hashtag") String hashtag) {
        List<Post> posts = postService.findAllByContentContaining(hashtag);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
