package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;



import java.util.List;
import java.util.Set;

public class RoleDto {

    private Long id;
    private String name;
    private List<String> users;

    // Constructors

    public RoleDto() {
        // Default constructor
    }

    public RoleDto(Long id, String name, List<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleDto(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }


    public String getName() {
        return name;
    }
}
