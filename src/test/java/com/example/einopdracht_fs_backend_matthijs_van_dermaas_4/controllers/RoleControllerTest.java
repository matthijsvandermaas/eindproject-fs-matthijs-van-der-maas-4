package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.RoleDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @Test
    void testGetAllRoles() {
        // Mock data
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setRoleName("ROLE_USER");

        List<RoleDto> mockRoles = Collections.singletonList(roleDto);

        // Stubbing the service method
        when(roleService.getRoles()).thenReturn(mockRoles);

        // Call the controller method
        ResponseEntity<List<RoleDto>> responseEntity = roleController.getAllRoles();

        // Verify the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify the response body
        List<RoleDto> responseBody = responseEntity.getBody();
        assertEquals(mockRoles, responseBody);
    }
}
