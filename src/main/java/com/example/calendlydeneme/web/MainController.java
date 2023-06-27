package com.example.calendlydeneme.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/customerLogin")
    public String home() {

        return "customerLogin";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "adminLogin";
    }



}


