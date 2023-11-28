package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    private AuthenticationService authenticatedUserService;



    public LoginController(UserService userService, AuthenticationService authenticatedUserService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/admin")
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


    @PostMapping("/api/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        try {
            // Perform authentication
            Authentication authentication = authenticatedUserService.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

            // Authentication successful, set the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect to the home page after successful login
            return "redirect:/home";
        } catch (AuthenticationException e) {
            // Authentication failed, show error message
            model.addAttribute("error", "Invalid username or password");
            return "home";
        }
    }
}


