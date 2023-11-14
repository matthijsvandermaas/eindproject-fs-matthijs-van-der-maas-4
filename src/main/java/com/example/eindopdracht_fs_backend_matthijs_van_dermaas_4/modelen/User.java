package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    //    Relation with Role ManyToMany.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    //    Relation with Profile OneToOne.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    Profile profile;

    public User() {
        this.profile = new Profile();
    }


    // Getters and setters

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = (List<Role>) roles;
    }

    // Method to manage roles

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public String getLastName() {
        return this.profile != null ? this.profile.getLastName() : null;
    }


    public void setFirstName(String firstName) {
        this.profile.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.profile.setLastName(lastName);
    }

    public void setEmail(String email) {
        this.profile.setEmail(email);
    }

    public void setCompany(String company) {
        this.profile.setCompany(company);
    }

    public String getFirstName() {
        return this.profile.getFirstName();
    }

//    public String getLastName() {
//        return this.profile.getLastName();
//    }

    public String getEmail() {
        return this.profile.getEmail();
    }

    public String getCompany() {
        return this.profile.getCompany();
    }
}



