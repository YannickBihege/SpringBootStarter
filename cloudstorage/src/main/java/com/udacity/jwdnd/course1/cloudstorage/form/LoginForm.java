package com.udacity.jwdnd.course1.cloudstorage.form;

public class LoginForm {
    /**
     * Accoring to Spring best practices, each form (formular) requires
     * a corresponding bean. (Class)
     */
    private String username;
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
