package com.example.calendlydeneme.web;

import com.example.calendlydeneme.entities.Role;
import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.UserRepository;
import com.example.calendlydeneme.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String listUsers(Long id,Model model) {
        model.addAttribute("users", service.listAll());
        return "users";
    }



    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.get(id);
        List<Role> listRoles = service.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        service.save(user);

        return "redirect:/users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long usersId){
        userRepo.deleteById(usersId);
        return "redirect:/users";
    }

}
