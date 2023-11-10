package com.udacity.jwdnd.course1.cloudstorage.dto;

public class SignupResponseDto {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignupResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}

