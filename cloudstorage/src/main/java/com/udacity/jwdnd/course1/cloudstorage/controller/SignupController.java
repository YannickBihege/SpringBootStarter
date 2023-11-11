package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/signup")
    public String signupView(@ModelAttribute("signupInput") SignupForm signupInput, Model model) {
        return "signup";
    }

    @PostMapping("/signupUser")
    public String signupUser(@ModelAttribute("signupInput") SignupForm signupInput, Model model) {
        String signupError = null;

        if (!userService.isUsernameAvailable(signupInput.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(signupInput.getFirstName(), signupInput.getLastName(), signupInput.getUsername(), signupInput.getPassword());
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }


}
