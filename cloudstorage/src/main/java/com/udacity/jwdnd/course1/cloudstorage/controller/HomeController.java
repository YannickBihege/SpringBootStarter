package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    /**
     * Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     *
     * if (principal instanceof UserDetails) {
     *   String username = ((UserDetails)principal).getUsername();
     * } else {
     *   String username = principal.toString();
     * }
     */

    // Get the authenticated user
    //SecurityContext context = SecurityContextHolder.getContext();
    //Authentication authentication = context.getAuthentication();
    //String username = authentication.getName();

    //User user = userService.getUserByUsername(username);
    //Integer userId = user.getUserId();

    private User getPrincipal(){
        User user = null;
        if(SecurityContextHolder.getContext().getAuthentication() instanceof User){
            user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }


    @Autowired
    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService,
                          UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService= credentialService;
        this.userService= userService;
    }

    @GetMapping("/api/home")
    public String home(Model model) {
        model.addAttribute("title", "File Note Handler");
        // You can add any necessary model attributes here
        /*
        model.addAttribute("files", fileService.getFileByUserId(userId));
        model.addAttribute("notes", noteService.getNotesByUserId(userId));
        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));
        */

        return "home"; //
    }


}
