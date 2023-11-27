package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Product aanmaken
    @Transactional
    @JsonIgnore
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            Product p = new Product();
            p.setProductName(productDto.getProductName());
            p.setNameBrewer(productDto.getNameBrewer());
            p.setProductionLocation(productDto.getProductionLocation());
            p.setTast(productDto.getTast());
            p.setType(productDto.getType());
            p.setAlcohol(productDto.getAlcohol());
            p.setIbu(productDto.getIbu());
            p.setColor(productDto.getColor());
            p.setVolume(productDto.getVolume());
            productRepository.save(p);

            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //user ophalen
    public ProductDto getProductByProductName(String productName) {
        Product product = productRepository.findById(productName).orElseThrow(() -> new RuntimeException("Product not found with productName: " + productName));
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setNameBrewer(product.getNameBrewer());
        productDto.setProductionLocation(product.getProductionLocation());
        productDto.setTast(product.getTast());
        productDto.setType(product.getType());
        productDto.setAlcohol(product.getAlcohol());
        productDto.setIbu(product.getIbu());
        productDto.setColor(product.getColor());
        productDto.setVolume(product.getVolume());
        return productDto;
    }
    //alle product ophalen
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductName(product.getProductName());
            productDto.setNameBrewer(product.getNameBrewer());
            productDto.setProductionLocation(product.getProductionLocation());
            productDto.setTast(product.getTast());
            productDto.setType(product.getType());
            productDto.setAlcohol(product.getAlcohol());
            productDto.setIbu(product.getIbu());
            productDto.setColor(product.getColor());
            productDto.setVolume(product.getVolume());
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public String deleteProduct(@RequestBody String productName) {
        if (productRepository.existsByProductName(productName)) {
            productRepository.deleteByProductName(productName);
        } else {
            throw new IdNotFoundException("Profile not found with product name: " + productName);
        }

        return "Profile deleted";
    }

}





