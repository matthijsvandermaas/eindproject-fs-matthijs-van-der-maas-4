package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.AuthRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/producenten")
public class ProducentController {
    private final ProducentService producentService;
    private ProducentRepository producentRepository;
    private AuthRepository authorityRepository;


    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService, AuthRepository authorityRepository) {
        this.producentService = producentService;
        this.producentRepository = producentRepository;
        this.authorityRepository = authorityRepository;

    }
    @GetMapping
    public ResponseEntity<List<ProducentDto>> getAllProducenten() {
        List<ProducentDto> pDto = producentService.getAllProfile();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducentDto> getOneProducent(@PathVariable Long id) {
        ProducentDto pDto = ProducentService.getProducent(id);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProducentDto> createProfile(@Valid @RequestBody ProducentDto producentDto) {
        ProducentDto pdto = producentService.createProducent(producentDto);
        return new ResponseEntity<>(pdto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProducentDto> deleteProducent(@PathVariable Long id) {
        producentService.deleteProducent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}