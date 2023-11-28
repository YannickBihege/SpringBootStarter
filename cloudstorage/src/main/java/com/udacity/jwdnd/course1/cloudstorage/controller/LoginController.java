package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;




    public LoginController(UserService userService ) {
        this.userService = userService;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/api/admin")
    public String adminHello() {
        return "Hello Admin";
    }


    @RolesAllowed("USER")
    @RequestMapping("/api/login")
    public String getLoginPage(Model model, @ModelAttribute("loginInput") LoginForm loginInput) {
        model.addAttribute("loggedOut", false);
        model.addAttribute("invalidCredentials", false);

        return "login";
    }


    @PostMapping("/api/perform_login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

        return "";
    }
}


