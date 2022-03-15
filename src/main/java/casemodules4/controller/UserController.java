package casemodules4.controller;

import casemodules4.model.User;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> showDetailUser(@PathVariable("idUser") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        userService.save(user);
        if (user == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User user1 = userService.findById(id);
        if (user1 == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setIdUser(id);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        User user = userService.findById(id);
        userService.deleteById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> findAllUserByName(@RequestParam("nameSearch") String nameSearch){
        List<User> users = userService.findAllByFullNameContaining(nameSearch);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
