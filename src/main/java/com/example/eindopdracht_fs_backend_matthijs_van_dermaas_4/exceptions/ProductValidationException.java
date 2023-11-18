package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions;

public class ProductValidationException extends RuntimeException {
    public ProductValidationException(String message, String eMessage) {
        super(message);
    }
}

