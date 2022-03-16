package casemodules4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("landing");
    }

//    @PostMapping("/check")
//    public ModelAndView newsfeedPost(){
//        return new ModelAndView("newsfeed");
//    }
}
