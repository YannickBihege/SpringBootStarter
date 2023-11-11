package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Controller for receiving requests.
 * This class needs to be a Spring Component so that Spring can
 * automatically create instances of it to receive web requests. We use
 * the @Controller annotation variation of @Component for this purpose.
 */
@Controller
//@RequestMapping("/home") // This is the simplest way to do it
public class HomeController {
    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;

    @Autowired
    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }


    //@GetMapping("/home")
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("myvar", "BihegeY File Handler");
        // You can add any necessary model attributes here
        // Add the necessary data to the model
        // model.addAttribute("files", fileService.getAllFiles());
        // model.addAttribute("notes", noteService.getAllNotes());
        // model.addAttribute("credentials", credentialService.getAllCredentials());
        return "home"; // This corresponds to the name of the Thymeleaf template (home.html)
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {

        InputStream fis = fileUpload.getInputStream();
        //
        return "redirect:/home";
    }

    @PostMapping("/addNote")
    public String addNote(@RequestParam String noteTitle, @RequestParam String noteDescription) {
        // Handle adding a new note here, e.g., save to a database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/editNote")
    public String editNote(@RequestParam Long noteId, @RequestParam String noteTitle, @RequestParam String noteDescription) {
        // Handle editing a note here, e.g., update in the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/deleteNote")
    public String deleteNote(@RequestParam Long noteId) {
        // Handle deleting a note here, e.g., remove from the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/addCredential")
    public String addCredential(@RequestParam String url, @RequestParam String username, @RequestParam String password) {
        // Handle adding a new credential here, e.g., save to a database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/editCredential")
    public String editCredential(@RequestParam Long credentialId, @RequestParam String url, @RequestParam String username, @RequestParam String password) {
        // Handle editing a credential here, e.g., update in the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/deleteCredential")
    public String deleteCredential(@RequestParam Long credentialId) {
        // Handle deleting a credential here, e.g., remove from the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }


}
