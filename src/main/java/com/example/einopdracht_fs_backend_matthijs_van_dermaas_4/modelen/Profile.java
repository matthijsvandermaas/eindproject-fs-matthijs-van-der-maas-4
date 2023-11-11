package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "username cannot be empty")
    private String UserName;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "firstname cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastname cannot be empty")
    private String lastName;
    private String company;
    @NotEmpty(message = "email cannot be empty")
    private String email;


    //    Relation with User OneToOne.
    @OneToOne
    User user;

    public Profile(Long id, String firstName, String lastName, String email, User user, String company, String UserName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user = user;
        this.company = company;
        this.UserName = UserName;
        this.password = password;
    }

    public Profile() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany(String company) {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUsername(String username) {
    }
}
