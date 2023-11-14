package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;



public class RoleDto {
    private Long id;

    private String roleName;

    public RoleDto(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
