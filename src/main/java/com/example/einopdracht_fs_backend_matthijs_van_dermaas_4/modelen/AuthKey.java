package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import java.io.Serializable;

public class AuthKey implements Serializable {
    private String username;
    private String authority;

    public AuthKey(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}