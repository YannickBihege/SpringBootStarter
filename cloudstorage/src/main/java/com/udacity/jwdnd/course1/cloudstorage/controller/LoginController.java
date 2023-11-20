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

    private final UserDetailsService userDetailsService;
    private UserService userService;

    public LoginController( UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }


    @RequestMapping("/api/login")
    public String getLoginPage(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        return "login";
    }

    @PostMapping("/api/login")
    public String login(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        // TODO
        boolean authenticationIsSuccessful = userService.authenticate(loginInput);

        if (authenticationIsSuccessful) {
            model.addAttribute("invalidCredentials", false);
            model.addAttribute("loggedOut", false);

            return "redirect:/api/home";
        } else {
            model.addAttribute("invalidCredentials", true);
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


}
