package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class NoteController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;


    // Get the authenticated user
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String username = authentication.getName();

    User user = userService.getUserByUsername(username);
    Integer userId = user.getUserId();


    public NoteController(FileService fileService, NoteService noteService, CredentialService credentialService,
                          UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService= credentialService;
        this.userService= userService;
    }
    @PostMapping("/api/home/addNote")
    public String addNote(@RequestParam String noteTitle, @RequestParam String noteDescription) {
        // Handle adding a new note here, e.g., save to a database
        // Redirect back to the home page or show a success message
        // insert notettitle, notedescription and userid
        //noteService.createOrUpdate(new Note(1,noteTitle,noteDescription,1));

        return "redirect:/home";
    }

    @PostMapping("/api/home/editNote")
    public String editNote(@RequestParam Long noteId, @RequestParam String noteTitle, @RequestParam String noteDescription) {
        // Handle editing a note here, e.g., update in the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/api/home/deleteNote")
    public String deleteNote(@RequestParam Long noteId) {
        // Handle deleting a note here, e.g., remove from the database
        // Redirect back to the home page or show a success message
        noteService.delete(Math.toIntExact(noteId));
        return "redirect:/home";
    }

}
