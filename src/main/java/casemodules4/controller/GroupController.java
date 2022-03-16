package casemodules4.controller;

import casemodules4.model.Account;
import casemodules4.security.jwt.response.ResponseMessage;
import casemodules4.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {
//    @Autowired
//    AccountService accountService;
//    public ResponseEntity<?> register(@RequestBody Account account){
//        if (accountService.existsByEmail(account.getEmail())) {
//            return new ResponseEntity<>(new ResponseMessage())
//        }
//    }
}
