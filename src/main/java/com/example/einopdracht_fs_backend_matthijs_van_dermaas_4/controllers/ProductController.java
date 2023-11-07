package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ParticulierDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.AuthRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ParticulierRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ParticulierService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/producten")
public class ProductController {
    private final ProductService productService;
    private ProductRepository productRepository;
    private AuthRepository authorityRepository;


    public ProductController(ProductService productService, ProductRepository productRepository, AuthRepository authorityRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.authorityRepository = authorityRepository;
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllData() {
        List<ProductDto> pDto = productService.getAllData();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOneParticulier(@PathVariable Long id) {
        ProductDto pDto = ProductService.getProduct(id);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createData(@Valid @RequestBody ProductDto productDto) {
        ProductDto pdto = productService.createProduct(productDto);
        return new ResponseEntity<>(pdto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}