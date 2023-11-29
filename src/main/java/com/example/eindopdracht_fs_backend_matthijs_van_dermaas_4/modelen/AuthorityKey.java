package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;


import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class AuthorityKey implements Serializable {
    private String username;
    private String authority;
}