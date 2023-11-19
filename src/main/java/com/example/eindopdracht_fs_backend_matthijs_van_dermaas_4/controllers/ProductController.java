package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RoleNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.ProductService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
           this.productService = productService;
            this.productRepository = productRepository;
    }
    //create product
    @PostMapping(value = "/createProduct")
    @JsonIgnore
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) throws RoleNotFoundException {
        try {
            productService.createProduct(productDto);
            System.out.println("Received user data: " + productDto.toString());
            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //get all users
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllUsers() {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            List<ProductDto> productDtos = products.stream()
                    .map(ProductDto::fromEntity)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get user by id
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) throws RoleNotFoundException {
        try {
            ProductDto productDto = productService.getProductById(id);
            if (productDto != null) {
                System.out.println("Request received for product id: " + id);
                return new ResponseEntity<>(productDto, HttpStatus.OK);

            } else {
                System.out.println("Product not found");
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            System.out.println("Request received for product id: " + id);
            return new ResponseEntity<>("Invalid productId format", HttpStatus.BAD_REQUEST);
        }
    }
}
