package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.config.FileStorageConfig;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final FileDocumentRepository fileDocumentRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, FileDocumentRepository fileDocumentRepository) {
        this.productRepository = productRepository;
        this.fileDocumentRepository = fileDocumentRepository;
    }

    @Autowired
    private FileStorageConfig fileStorageConfig;

    public static String saveFile(MultipartFile file, FileStorageConfig fileStorageConfig) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        Path targetPath = Paths.get(fileStorageConfig.uploadDir() + fileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public void createProductWithPhoto(MultipartFile file, String productName, String nameBrewer,
                                       String productionLocation, String tast, String type,
                                       double alcohol, double ibu, String color, double volume) throws IOException {
        String photoFileName = FileStorageConfig.saveFile(file, fileStorageConfig);

        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setNameBrewer(nameBrewer);
        newProduct.setProductionLocation(productionLocation);
        newProduct.setTast(tast);
        newProduct.setType(type);
        newProduct.setAlcohol(alcohol);
        newProduct.setIbu(ibu);
        newProduct.setColor(color);
        newProduct.setVolume(volume);
        newProduct.setPhotoFileName(photoFileName);

        productRepository.save(newProduct);
    }

    @Transactional
    public ResponseEntity<String> createProduct(ProductDto productDto) {
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

    public ProductDto getProductById(Long userId) {
        Product product = productRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        ProductDto productDto = new ProductDto();
        productDto.add(ProductDto.fromEntity(product));
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(ProductDto.fromEntity(product));
        }
        return productDtos;
    }
}
