package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Authority;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

public class UserDto {
    private Long id;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastname cannot be empty")
    private String lastName;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    private String company;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "roles cannot be empty")
    private List<String> roles;

    // Constructors
    public UserDto() {
        // Default constructor
    }
    public UserDto(Long id, String username, String firstName, String lastName, String email, String company, String password, List<String> roles) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.password = password;
        this.roles = roles;
    }
//Methodes
    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    // Getters and Setters
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
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
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
    // methodes
    public Set<Authority> getAuthorities() {
        return null;
    }
    public void addRole(String user) {
    }
}
