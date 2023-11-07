package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }

    public CustomAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
