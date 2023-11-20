package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class SignupController {
    private final UserService userService;
    private final HashService hashService;
   private final EncryptionService encryptionService;

    private final static String encryptionKey = "key";



    public SignupController(UserService userService, HashService hashService ,EncryptionService encryptionService ) {
        this.userService = userService;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/api/signup")
    public String signupView(@ModelAttribute("signupInput") SignupForm signupInput, Model model) {
        return "signup";
    }

    @PostMapping("/api/signup")
    public String signupUser(@ModelAttribute("signupInput") SignupForm signupInput, Model model) throws Exception {
        String signupError = null;

        if (!userService.isUsernameAvailable(signupInput.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {

            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encryptedPassword = encryptionService.encryptValue(signupInput.getPassword(),encryptionKey,encodedSalt);



            int rowsAdded = userService.saveUser(new User(null,signupInput.getUsername(), encodedSalt, encryptedPassword, signupInput.getFirstName(), signupInput.getLastName()));
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
