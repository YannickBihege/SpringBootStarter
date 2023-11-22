package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(  UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api/login")
    public String getLoginPage(Model model, @ModelAttribute("loginInput") LoginForm loginInput) {
        model.addAttribute("loggedOut", false);
        model.addAttribute("invalidCredentials", false);

        return "login";
    }



}
