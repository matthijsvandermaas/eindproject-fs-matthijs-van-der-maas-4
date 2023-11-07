package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsersAndUserDto {
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "This needs to be an email address")
    private String email;
    @NotEmpty(message = "There needs to be a role")
    private String[] roles;

    public UsersAndUserDto(String username, String password, String firstName, String lastName, String email, @NotEmpty(message = "There needs to be a role") String[] roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {

    }

    public Object getApikey() {

        return getApikey();
    }

    public Profile getProfile() {
        this.getProfile();
        return getProfile();
    }
}
