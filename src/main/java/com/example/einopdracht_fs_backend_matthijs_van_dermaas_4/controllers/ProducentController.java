package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.AuthorityRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/producenten")
public class ProducentController {
    private final ProducentService producentService;
    private ProducentRepository producentRepository;
    private AuthorityRepository rolesRepository;
    private final PasswordEncoder encoder;
    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService, AuthorityRepository rolesRepository, PasswordEncoder encoder, PasswordEncoder encoder1) {
        this.producentService = producentService;
        this.producentRepository = producentRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<String> createProducent(@RequestBody ProducentDto producentDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(producentService.createProducent(producentDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> getProducentById(@PathVariable Long id) {
        Producent producent = producentService.getProducentById(id);
        if (producent != null) {
            return ResponseEntity.ok(producent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateProducent(@PathVariable Long id, @RequestBody ProducentDto producentDto) {
        try {
            producentService.updateProducent(id, producentDto);
            return ResponseEntity.ok("Producent met ID " + id + " is bijgewerkt.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het bijwerken: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteProducent(@PathVariable Long id) {
        try {
            producentService.deleteProducent(id);
            return ResponseEntity.ok("Producent met ID " + id + " is verwijderd.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het verwijderen: " + e.getMessage());
        }
    }
}