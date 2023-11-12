package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class UserDto {
    @Id
    private Long id;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastname cannot be empty")
    private String lastName;
    private String company;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    private String[] roles;

    public UserDto(Long id, String username, String password, String firstName, String lastName, String company, String email, String[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.roles = roles;
    }

    public UserDto() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
        this.roles = roles;
    }

    public Object getSomeProperty() {
        return getUsername();
    }
}