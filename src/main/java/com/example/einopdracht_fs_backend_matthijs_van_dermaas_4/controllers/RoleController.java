package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.RoleDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> rdto = roleService.getRoles();
        return new ResponseEntity<>(rdto, HttpStatus.OK);

    }
}