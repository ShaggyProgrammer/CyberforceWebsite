package com.vitavehiculum.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.*;
import java.util.Arrays;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class WebController {

    //Home
    @GetMapping("/")
    public String home(){
        return "index.html";

    }

    //Contact
    @GetMapping("/contact")
    public String contact(){
        return "contact.html";
    }

    //Manufacturing
    @GetMapping("/manufacturing")
    public String manufacturing(){
        return "manufacturing.html";

    }

    //Manufacturing
    @GetMapping("/solar")
    public String solar(){
        return "solar.html";

    }

    //Login
    @GetMapping("/login")
    public String login(){
        return "login.html";

    }

}
