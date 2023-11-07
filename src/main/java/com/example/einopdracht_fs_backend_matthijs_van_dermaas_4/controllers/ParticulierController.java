package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ParticulierDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.AuthRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ParticulierRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ParticulierService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/particulieren")
public class ParticulierController {
    private final ParticulierService particulierService;
    private ParticulierRepository particulierRepository;
    private AuthRepository authorityRepository;


    public ParticulierController(ParticulierService particulierService, ParticulierRepository particulierRepository, AuthRepository authorityRepository) {
        this.particulierService = particulierService;
        this.particulierRepository = particulierRepository;
        this.authorityRepository = authorityRepository;
    }
    @GetMapping
    public ResponseEntity<List<ParticulierDto>> getAllProfile() {
        List<ParticulierDto> pDto = particulierService.getAllProfile();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticulierDto> getOneParticulier(@PathVariable Long id) {
        ParticulierDto pDto = ParticulierService.getParticulier(id);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParticulierDto> createProfile(@Valid @RequestBody ParticulierDto particulierDto) {
        ParticulierDto pdto = particulierService.createParticulier(particulierDto);
        return new ResponseEntity<>(pdto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParticulierDto> deleteParticulier(@PathVariable Long id) {
        particulierService.deleteParticulier(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}