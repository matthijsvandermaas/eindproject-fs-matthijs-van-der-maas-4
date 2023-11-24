package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import jakarta.validation.constraints.NotEmpty;

public class AuthenticationRequest {
@NotEmpty(message = "username is required")
    private String username;
@NotEmpty(message = "password is required")
    private String password;
@NotEmpty(message = "role is required")
    private String role;

    public AuthenticationRequest() {
    }
    public AuthenticationRequest(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
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
    public void setRole(String role) { this.role = role; }
    public String getRole() { return role;}
}