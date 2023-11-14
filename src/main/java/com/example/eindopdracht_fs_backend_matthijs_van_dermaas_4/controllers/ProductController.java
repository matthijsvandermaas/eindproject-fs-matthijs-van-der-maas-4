package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producten")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDto = productService.getAllProducts();
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto newProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
