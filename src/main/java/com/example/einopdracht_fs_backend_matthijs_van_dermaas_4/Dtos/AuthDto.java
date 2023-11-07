package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import jakarta.validation.constraints.NotEmpty;

public class AuthDto {
    @NotEmpty (message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Confirm password cannot be empty")
//    @Min(value = 8, message = "Password need to contain at least 8 characters")
    private String password;

    public AuthDto(String username, String password) {
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