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

    public void addAuthority(Authority authority) {
    }

    public void removeAuthority(Authority authorityToRemove) {
    }

    public Collection<Object> getAuthorities() {
        return getAuthorities();
    }


    public void setProfile(Profile profile) {
    }
}
