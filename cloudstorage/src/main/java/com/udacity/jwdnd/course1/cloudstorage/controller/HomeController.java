package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class HomeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
  ;

    @Autowired
    public HomeController(FileService fileService, NoteService noteService) {
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @RequestMapping("/api/home")
    public String home(Model model) {
        model.addAttribute("myvar", "BihegeY File Handler");
        // You can add any necessary model attributes here
        //model.addAttribute("files", fileService.);
        //model.addAttribute("notes", noteService.getNotesByUserId());
        //model.addAttribute("credentials", credentialService.getCredentialById());
        return "home"; // This corresponds to the name of the Thymeleaf template (home.html)
    }

    @PostMapping("/api/home/file-upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        InputStream fis = fileUpload.getInputStream();
        return "redirect:/home";
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

    @PostMapping("/api/home/addCredential")
    public String addCredential(@RequestParam String url, @RequestParam String username, @RequestParam String key, @RequestParam char[] password, @RequestParam String userId) {
        // Handle adding a new credential here, e.g., save to a database
        // Redirect back to the home page or show a success message
        // (url, userName, key, password, userId)
        //credentialService.insert(new Credential(url,username,key,password,userId));
        return "redirect:/home";
    }

    @PostMapping("/api/editCredential")
    public String editCredential(@RequestParam Long credentialId, @RequestParam String url, @RequestParam String username, @RequestParam String password) {
        // Handle editing a credential here, e.g., update in the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }

    @PostMapping("/api/deleteCredential")
    public String deleteCredential(@RequestParam Long credentialId) {
        // Handle deleting a credential here, e.g., remove from the database
        // Redirect back to the home page or show a success message
        return "redirect:/home";
    }


}
