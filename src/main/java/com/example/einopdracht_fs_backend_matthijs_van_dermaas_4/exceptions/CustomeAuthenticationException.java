package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomeAuthenticationException extends AuthenticationException {
    public CustomeAuthenticationException(String msg) {
        super(msg);
    }

    public CustomeAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
