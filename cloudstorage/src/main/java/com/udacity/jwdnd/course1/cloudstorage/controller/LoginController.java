package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
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

    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        boolean loggedOut = false;
        model.addAttribute("loggedOut", false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {

        if (userService.authenticate(loginInput.getUsername(), loginInput.getPassword())) {
            model.addAttribute("invalidCredentials", false);
            return "redirect:/home";
        } else {
            // Invalid username or password
            model.addAttribute("invalidCredentials", true);
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


}
