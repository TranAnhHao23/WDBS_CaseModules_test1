package casemodules4.controller;

import casemodules4.model.FriendList;
import casemodules4.model.Post;
import casemodules4.model.User;
import casemodules4.service.IFriendListService;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IFriendListService friendListService;

    @Value("${upload.path}")
    private String fileUpload;

    @Value("${view}")
    private String view;




//    @GetMapping("/{idUser}")
//    public ModelAndView showAll(@PathVariable("idUser") Long idUser){
//        ModelAndView modelAndView = new ModelAndView("newsfeed");
//        User user = userService.findById(idUser);
//        List<Post> posts = postService.findAllByUserPostIdUser(idUser);
//        modelAndView.addObject("posts",posts);
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/list/{idUser}")
    public ResponseEntity<User> showDetailUser(@PathVariable("idUser") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        MultipartFile multipartFile = user.getImgFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(user.getImgFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setImgUrl(fileName);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User user1 = userService.findById(id);
        if (user1 == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = user.getImgFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(user.getImgFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setImgUrl(fileName);
        user.setIdUser(id);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        userService.deleteById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/find/{accountId}")
    public ResponseEntity<User> getUserByAccountId(@PathVariable("accountId") Long id) {
        User user = userService.findByAccount_Id(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> findAllUserByName(@RequestParam("nameSearch") String nameSearch) {
        List<User> users = userService.findAllByFullNameContaining(nameSearch);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{idUser}/friend-list")
    public ResponseEntity<List<User>> findAllFriendByIdUser(@PathVariable("idUser") Long id) {
        List<User> users = getListFriendList(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{idUser}/non-friend-list")
    public ResponseEntity<List<User>> findAllNonFriendByIdUser(@PathVariable("idUser") Long id) {
        List<User> usersNonFriend = userService.findAll();
        List<User> usersFriend = getListFriendList(id);
        usersNonFriend.remove(userService.findByAccount_Id(id));
        usersNonFriend.removeAll(usersFriend);
        return new ResponseEntity<>(usersNonFriend, HttpStatus.OK);
    }

    @GetMapping("/{idUser}/pending")
    public ResponseEntity<List<User>> findAllPendingByIdUser(@PathVariable("idUser") Long id) {
        List<FriendList> friendLists = friendListService.findAllPendingByIdUser(id);
        List<User> users = new ArrayList<>();
        for (FriendList friendList : friendLists) {
            users.add(friendList.getUserFrom());
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{idUser}/changeAvatar")
    public ResponseEntity<User> changeAvatar(@PathVariable("idUser") Long id, @RequestBody MultipartFile data){
        User user = userService.findById(id);

        String fileName = data.getOriginalFilename();
        try {
            FileCopyUtils.copy(data.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setImgUrl(view + fileName);
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public List<User> getListFriendList(Long idUser) {
        List<FriendList> friendLists = friendListService.findFriendListByIdUser(idUser);
        List<User> users = new ArrayList<>();
        for (FriendList friendList : friendLists) {
            if (Objects.equals(friendList.getUserFrom().getIdUser(), idUser)) {
                users.add(friendList.getUserTo());
            } else {
                users.add(friendList.getUserFrom());
            }
        }
        return users;
    }

}
