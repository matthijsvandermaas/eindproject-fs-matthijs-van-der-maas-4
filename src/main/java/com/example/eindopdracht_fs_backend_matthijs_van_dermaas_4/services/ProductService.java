package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
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
    private final FileDocumentRepository docFileRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, FileDocumentRepository docFileRepository){
        this.productRepository = productRepository;
        this.docFileRepository = docFileRepository;
    }
    //producten aanmaken
    @Transactional
    @JsonIgnore
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            Product p = new Product();
            p.setProductName(productDto.getProductName());
            p.setNameBrewer(productDto.getNameBrewer());
            p.setProductionLocation(productDto.getProductionLocation());
            p.setType(productDto.getType());
            p.setAlcohol(productDto.getAlcohol());
            p.setIbu(productDto.getIbu());
            p.setColor(productDto.getColor());
            p.setTast(productDto.getTast());
            p.setVolume(productDto.getVolume());

            productRepository.save(p);

            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //user ophalen
    public ProductDto getProductById(Long userId) {
        Product product  = productRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setNameBrewer(product.getNameBrewer());
        productDto.setProductionLocation(product.getProductionLocation());
        productDto.setType(product.getType());
        productDto.setAlcohol(product.getAlcohol());
        productDto.setIbu(product.getIbu());
        productDto.setColor(product.getColor());
        productDto.setTast(product.getTast());
        productDto.setVolume(product.getVolume());


        return productDto;
    }

    //alle users ophalen
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setNameBrewer(product.getNameBrewer());
            productDto.setProductionLocation(product.getProductionLocation());
            productDto.setType(product.getType());
            productDto.setAlcohol(product.getAlcohol());
            productDto.setIbu(product.getIbu());
            productDto.setColor(product.getColor());
            productDto.setTast(product.getTast());
            productDto.setVolume(product.getVolume());

            productDtos.add(productDto);

        }
        return productDtos;
    }

}
