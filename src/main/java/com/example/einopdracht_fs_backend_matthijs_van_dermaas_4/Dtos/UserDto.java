package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class UserDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty (message = "Password cannot be empty")
    private String password;

    private String[] roles;

    public UserDto(String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto() {

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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public void setApikey(String randomString) {

    }

    public Set<Authority> getAuthorities() {
        this.getAuthorities();
        return getAuthorities();
    }
}