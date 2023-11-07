package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException() {
        super();
    }
}
