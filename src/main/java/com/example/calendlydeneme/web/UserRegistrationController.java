package com.example.calendlydeneme.web;

import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.RoleRepository;
import com.example.calendlydeneme.repositories.UserRepository;
import com.example.calendlydeneme.services.UserService;
import com.example.calendlydeneme.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private UserRepository userRepo;
    private UserServiceImpl service;
    private RoleRepository roleRepo;

    public UserRegistrationController(UserServiceImpl service) {
        super();
        this.service = service;
    }



    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(Model model, User user) {
        service.registerDefaultUser(user);
        model.addAttribute("user",user);
        return "redirect:/registration?success";
    }

}
