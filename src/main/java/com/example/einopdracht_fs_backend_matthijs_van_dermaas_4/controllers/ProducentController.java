package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.AuthorityRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/producenten")
public class ProducentController {
    private final ProducentService producentService;
    private ProducentRepository producentRepository;
    private AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;
    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService, PasswordEncoder encoder, AuthorityRepository authorityRepository) {
        this.producentService = producentService;
        this.producentRepository = producentRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producent> getProducerById(@PathVariable Long id) {
        Producent producent = producentService.getProducerById(id);
        if (producent != null) {
            return new ResponseEntity<>(producent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Producent> createProducer(@RequestBody Producent producent) {
        Producent newProducer = producentService.createProducer(producent);
        return new ResponseEntity<>(newProducer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producent> updateProducer(@PathVariable Long id, @RequestBody Producent producent) {
        Producent updatedProducer = producentService.updateProducer(id, producent);
        if (updatedProducer != null) {
            return new ResponseEntity<>(updatedProducer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducer(@PathVariable Long id) {
        boolean deleted = producentService.deleteProducer(id);
        if (deleted) {
            return new ResponseEntity<>("Producer deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producer not found", HttpStatus.NOT_FOUND);
        }
    }
}