package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    public Role() {

    }

    public Role(String roleName, List<User> users) {
        this.roleName = roleName;
        this.users = users;
    }
    // Getters and setters

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public String getName() {
        return getRoleName();
    }
    public Role(String roleName) {
        this.roleName = roleName;
    }
}
