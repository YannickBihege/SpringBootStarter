package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);



    @Autowired
    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService,
                          UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService= credentialService;
        this.userService= userService;
    }

    /*
    @RequestMapping("/api/home")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("myvar", "File Note Handler");

        String loggedInUserName = (String) authentication.getPrincipal();
        User user = userService.getUserByUsername(loggedInUserName);
        int userId = user.getUserId();
        // You can add any necessary model attributes here
        model.addAttribute("files", fileService.getFileByUserId(userId));
        model.addAttribute("notes", noteService.getNotesByUserId(userId));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));

        return "home"; //
    }
    */

    @GetMapping("/api/home")
    public String signupView(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        return "home";
    }


    @PostMapping("/api/login")
    public String login(Authentication authentication, Model model, @ModelAttribute("loginInput") LoginForm loginInput) {
        String loggedInUserName = (String) authentication.getPrincipal();
        User user = userService.getUserByUsername(loggedInUserName);

        logger.info("loggedInUserName"+loggedInUserName);
        logger.info("user"+ user.toString());


        String username = loginInput.getUsername();
        String password = loginInput.getPassword();
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        int userId = user.getUserId();
        logger.info("userId" +userId);


        // You can add any necessary model attributes here
        model.addAttribute("files", fileService.getFileByUserId(userId));
        model.addAttribute("notes", noteService.getNotesByUserId(userId));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));

        return "home";
    }



}
