package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    //    Relation with Role ManyToMany.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    //    Relation with Profile OneToOne.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    Profile profile;
    private String email;


    public User(String username, String password, List<Role> roles, Profile profile) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.profile = profile;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = String.valueOf(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
    

    // Method to manage roles

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public void addAuthority(Authority authority) {
    }

    public void removeAuthority(Authority authorityToRemove) {
    }

    public Collection<Object> getAuthorities() {
        return getAuthorities();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApikey(Object apikey) {
    }

    public void setRoles(String[] roles) {
    }
}
