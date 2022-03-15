package casemodules4.controller;

import casemodules4.model.FriendList;
import casemodules4.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/friend-list")
public class FriendListController {
    @Autowired
    private IFriendListService friendListService;

    @GetMapping
    public ResponseEntity<List<FriendList>> showAllFriendList() {
        List<FriendList> friendLists = friendListService.findAll();
        return new ResponseEntity<>(friendLists, HttpStatus.OK);
    }

    @GetMapping("/{idFriendList}")
    public ResponseEntity<FriendList> showDetailFriendList(@PathVariable("idFriendList") Long id) {
        FriendList friendList = friendListService.findById(id);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FriendList> createNewFriendList(@RequestBody FriendList friendList) {
        FriendList friendList1 = friendListService.save(friendList);
        if (friendList1 == null) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendList,HttpStatus.OK);
    }

    @GetMapping("delete")
    public void deleteFriendList(@RequestParam("userFromId")Long userFromId,@RequestParam("userToId")Long userToId){
        friendListService.unFriend(userFromId, userToId);
    }

    @GetMapping("/accept")
    public void acceptFriendRequest(@RequestParam("userFromId")Long userFromId,@RequestParam("userToId")Long userToId){
        friendListService.acceptFriendRequest(userToId, userFromId);
    }

    @GetMapping("/block")
    public void blockFriendRequest(@RequestParam("userFromId")Long userFromId,@RequestParam("userToId")Long userToId){
        friendListService.blockFriend(userToId, userFromId);
    }

}
