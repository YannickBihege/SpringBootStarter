package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.security.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private final UserDetailsService userDetailsService;

    private final AuthenticationService authenticationService;
    private UserService userService;

    public LoginController(UserDetailsService userDetailsService, AuthenticationService authenticationService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }


    @RequestMapping("/api/login")
    public String getLoginPage(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        return "login";
    }



    @PostMapping("/api/login")
    public String login(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        String inputUsername = loginInput.getUsername();
        String inputPassword = loginInput.getPassword();

        // Authenticate the user
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(inputUsername, inputPassword);
        AuthenticationService authenticationManager = null;
        Authentication authentication = authenticationManager.authenticate(authRequest);

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Authentication successful, redirect to home
            return "redirect:/api/home";
        } else {
            // Authentication failed, show login page with error message
            model.addAttribute("invalidCredentials", true);
            return "login";
        }
    }


}
