package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/producten")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productService.createProduct(productDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }

    }
        @GetMapping("/{id}")
        public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
            try {
                ProductDto productDto = productService.getProductById(id);
                return ResponseEntity.ok(productDto);
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
}
