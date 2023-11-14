package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}