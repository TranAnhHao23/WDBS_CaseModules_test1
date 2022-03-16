package casemodules4.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("landing");
    }

    @GetMapping("/1")
    public ModelAndView newsfeed(){
        return new ModelAndView("newsfeed");
    }
}
