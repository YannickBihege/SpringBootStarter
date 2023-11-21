package com.udacity.jwdnd.course1.cloudstorage.controller.functionalities;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CredentialController {



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
