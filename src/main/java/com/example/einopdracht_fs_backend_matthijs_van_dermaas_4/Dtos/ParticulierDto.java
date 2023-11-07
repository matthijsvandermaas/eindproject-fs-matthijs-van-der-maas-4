package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ParticulierDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "voer hier je voornaam in")
    private String firstName;

    @NotEmpty (message = "voer hier je achternaam in")
    private String lastName;

    @NotEmpty (message = "voer hier je email in")
    @Email
    private String email;

    @NotEmpty(message = "voer hier je gebruikersnaam in")
    private String userName;

    @NotEmpty (message = "voer hier je wachtwoord in")
    private String password;
    @NotEmpty(message = "Roles must not be empty")
    @ElementCollection
    private List<String> roles = List.of("USER");

    public ParticulierDto(Long id, String firstName, String lastName, String email, String userName, String password, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public ParticulierDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

