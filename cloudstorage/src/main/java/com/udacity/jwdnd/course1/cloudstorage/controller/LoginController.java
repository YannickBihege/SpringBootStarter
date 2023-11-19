package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private UserService userService;

    public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }


    @RequestMapping("/api/login")
    public String getLoginPage(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        model.addAttribute("loggedOut", false);
        return "login";
    }

    @PostMapping("/api/login")
    public String login(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {

        if (userService.authenticate(loginInput.getUsername(), loginInput.getPassword())) {
            model.addAttribute("invalidCredentials", false);
            System.out.println("Authenticate");
            return "redirect:/api/home";
        } else {
            // Invalid username or password
            model.addAttribute("invalidCredentials", true);
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


}
