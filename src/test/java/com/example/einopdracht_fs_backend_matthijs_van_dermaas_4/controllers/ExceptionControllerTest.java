package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ExceptionControllerTest {

    private final ExceptionController exceptionController = new ExceptionController();

    @Test
    void testExceptionId() {
        // Mock data
        IdNotFoundException exception = new IdNotFoundException("Test exception");

        // Call the controller method
        ResponseEntity<Object> responseEntity = exceptionController.exceptionId(exception);

        // Verify the response status
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verify the response body
        Object responseBody = responseEntity.getBody();
        assertEquals("Test exception", responseBody);
    }
}
