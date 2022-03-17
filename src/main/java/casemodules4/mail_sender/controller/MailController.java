package casemodules4.mail_sender.controller;

import casemodules4.mail_sender.model.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    public JavaMailSender mailSender;

    @ResponseBody
    @GetMapping("/send")
    public String sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");
        this.mailSender.send(message);
        return "Email send";
    }
}
