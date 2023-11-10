package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ParticulierDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Particulier;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ParticulierRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticulierServiceTest {

    @Mock
    private ParticulierRepository particulierRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private ParticulierService particulierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetParticulierSuccess() {
        Long particulierId = 1L;
        Particulier mockParticulier = new Particulier();
        mockParticulier.setId(particulierId);
        mockParticulier.setFirstName("John");
        mockParticulier.setLastName("Doe");
        mockParticulier.setEmail("john.doe@example.com");

        when(particulierRepository.findById(particulierId)).thenReturn(Optional.of(mockParticulier));

        ParticulierDto result = ParticulierService.getParticulier(particulierId);

        verify(particulierRepository, times(1)).findById(particulierId);

        assertNotNull(result, "Result should not be null");

        if (!particulierId.equals(result.getId())) {
            System.out.println("Expected ID: " + particulierId);
            System.out.println("Actual ID: " + result.getId());
            throw new AssertionError("ID mismatch");
        }

        if (!"John".equals(result.getFirstName())) {
            throw new AssertionError("First name mismatch");
        }

        if (!"Doe".equals(result.getLastName())) {
            throw new AssertionError("Last name mismatch");
        }

        if (!"john.doe@example.com".equals(result.getEmail())) {
            throw new AssertionError("Email mismatch");
        }
    }



    @Test
    void testGetParticulierNotFound() {
        Long particulierId = 2L;
        when(particulierRepository.findById(particulierId)).thenReturn(Optional.empty());

        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () ->
                ParticulierService.getParticulier(particulierId));

        verify(particulierRepository, times(1)).findById(particulierId);

        assertEquals("Property not found with ID: " + particulierId, exception.getMessage());
    }

    @Test
    void testCreateParticulier() {
        ParticulierDto inputParticulierDto = new ParticulierDto();
        inputParticulierDto.setFirstName("John");
        inputParticulierDto.setLastName("Doe");
        inputParticulierDto.setEmail("john.doe@example.com");

        Particulier savedParticulier = new Particulier();
        savedParticulier.setId(1L);

        when(particulierRepository.save(any(Particulier.class))).thenReturn(savedParticulier);

        ParticulierDto result = particulierService.createParticulier(inputParticulierDto);

        verify(particulierRepository, times(1)).save(any(Particulier.class));

        assertNotNull(result, "Result should not be null");

        // Check if the ID is 1
        if (1L == result.getId()) {
            // Check other attributes
            if (!"John".equals(result.getFirstName())) {
                throw new AssertionError("First name mismatch");
            }
            if (!"Doe".equals(result.getLastName())) {
                throw new AssertionError("Last name mismatch");
            }
            if (!"john.doe@example.com".equals(result.getEmail())) {
                throw new AssertionError("Email mismatch");
            }
        } else {
            System.out.println("Expected ID: 1");
            System.out.println("Actual ID: " + result.getId());
            throw new AssertionError("ID mismatch");
        }
    }



    // Voeg meer tests toe voor andere methoden indien nodig
}
