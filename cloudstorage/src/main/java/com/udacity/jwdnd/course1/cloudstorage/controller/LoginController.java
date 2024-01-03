package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private UserService userService;
    private final EncryptionService encryptionService;
    private final static String encryptionKey = "key";


    public LoginController(UserService userService, EncryptionService encryptionService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/api/login")
    public String loginView(Model model, @ModelAttribute("loginInput") LoginForm loginInput) {
        return "login";
    }

    @PostMapping("/api/login")
    public String loginUser(@ModelAttribute("loginInput") LoginForm loginInput, Model model) throws Exception {
        String loginError = null;
        String inputUsername = loginInput.getUsername();
        String nonEncodedPassword = loginInput.getPassword();

        System.out.println("Login controller post");

        if (!userService.isUsernameAvailable(loginInput.getUsername())) {
            loginError = "The username does not exist.";
            return "login";
        }

        if (loginError == null){
            User retrievedUser = userService.getUserByUsername(inputUsername);
            String retrievedEncodedSalt = retrievedUser.getSalt();
            String encryptedPasswordRetrieved = encryptionService.encryptValue(loginInput.getPassword(),encryptionKey,retrievedEncodedSalt);

            if(encryptedPasswordRetrieved != retrievedUser.getPassword()){
                loginError = "User name or password Incorrect";
                model.addAttribute("loginError", loginError);
                model.addAttribute("invalidCredentials", true);
                return "login";

            }else{
                model.addAttribute("invalidCredentials", false);
                return "home";
            }
        }else{
            return "login";
        }


    }



}
