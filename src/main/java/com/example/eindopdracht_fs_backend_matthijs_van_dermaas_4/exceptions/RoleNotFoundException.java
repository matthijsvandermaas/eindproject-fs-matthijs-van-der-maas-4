package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String roleName) {
        super("Rol niet gevonden: " + roleName);
    }
}

