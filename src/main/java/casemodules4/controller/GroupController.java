package casemodules4.controller;

import casemodules4.model.Account;
import casemodules4.model.Group;
import casemodules4.security.jwt.response.ResponseMessage;
import casemodules4.service.IGroupService;
import casemodules4.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/{idUser}/list-group")
    public ResponseEntity<List<Group>> showAllGroup(@PathVariable("idUser") Long idUser){
        List<Group> groups = groupService.findAllByIdUser(idUser);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/{idUser}/join")
    public ResponseEntity<String> joinGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser){
        groupService.addToGroup(idGroup, idUser);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/{idGroup}/{idUser}/leave")
    public ResponseEntity<String> leaveGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idUser") Long idUser){
        groupService.leaveToGroup(idGroup, idUser);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


//    @Autowired
//    AccountService accountService;
//    public ResponseEntity<?> register(@RequestBody Account account){
//        if (accountService.existsByEmail(account.getEmail())) {
//            return new ResponseEntity<>(new ResponseMessage())
//        }
//    }
}
