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



    @GetMapping("/api/home")
    public String signupView(@ModelAttribute("loginInput") LoginForm loginInput, Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String loggedInUserName = (String) authentication.getPrincipal();
        User user = userService.getUserByUsername(loggedInUserName);
        int userId = user.getUserId();
        // You can add any necessary model attributes here
        model.addAttribute("files", fileService.getFileByUserId(userId));
        model.addAttribute("notes", noteService.getNotesByUserId(userId));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));

        return "home";
    }




}
