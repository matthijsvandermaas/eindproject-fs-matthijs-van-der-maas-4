package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RoleNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import java.util.stream.Collectors;


@RestController
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;


    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;

    }
    // create product
    @PostMapping("/createProduct")
    public ResponseEntity<String> createProductWithPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productName") String productName,
            @RequestParam("nameBrewer") String nameBrewer,
            @RequestParam("productionLocation") String productionLocation,
            @RequestParam("tast") String tast,
            @RequestParam("type") String type,
            @RequestParam("alcohol") double alcohol,
            @RequestParam("ibu") double ibu,
            @RequestParam("color") String color,
            @RequestParam("volume") double volume) {

        try {
            productService.createProductWithPhoto(file, productName, nameBrewer, productionLocation, tast, type, alcohol, ibu, color, volume);

            return ResponseEntity.ok("Product created successfully");
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating product: " + e.getMessage());
        }
    }

    //get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            List<ProductDto> productDtos = products.stream()
                    .map(ProductDto::fromEntity)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(productDtos, products.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get user by id
    @GetMapping("/product/{id}")
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
