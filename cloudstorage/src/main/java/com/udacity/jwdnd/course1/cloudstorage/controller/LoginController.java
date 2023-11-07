package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // This will return the login.html Thymeleaf template
    }

    // Add other login-related methods here
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        if (userService.authenticate(user.getUsername(), user.getPassword())) {
            // Successful login
            return "redirect:/home";
        } else {
            // Invalid username or password
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

}
