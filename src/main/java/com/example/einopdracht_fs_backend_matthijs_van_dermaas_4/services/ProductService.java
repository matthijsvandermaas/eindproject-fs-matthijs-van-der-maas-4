package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.FileUpload.FileLoader;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto productDto = new ProductDto();
            productToProductDTO(p, productDto);

            productDtos.add(productDto);
        }
        return productDtos;
    }


    private static void productToProductDTO(Product p, ProductDto productDto) {
        productDto.setProductName(p.getProductName());

    }

    private void productDTOToProduct(ProductDto productDTO, Product product) {

        product.setProductName(productDTO.getProductName());
        ;
    }

    public ProductDto getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);
            return (pdto);
        } else {
            throw new IdNotFoundException("Product not found with id: " + id);
        }
    }

    public ProductDto createProduct(ProductDto productDTO) {
        Product product = new Product();
        productDTOToProduct(productDTO, product);

        Product savedProduct = productRepository.save(product);

        ProductDto savedProductDto = new ProductDto();
        productToProductDTO(savedProduct, savedProductDto);

        return savedProductDto;
    }

    public String deleteProduct(@RequestBody Long id) {

        productRepository.deleteById(id);
        return "Product deleted";
    }

    public void saveProduct(Product product) {
    }

    public void saveProduct(Product product) throws IOException {
        String basePath = System.getProperty("user.dir") + "/uploads/";
        String fileName = product.getFileName();
        Path filePath = Paths.get(basePath, fileName);
        Files.write(filePath, product.getFile());

        String fileName2 = product.getFileName2();
        Path filePath2 = Paths.get(basePath, fileName2);
        Files.write(filePath2, product.getFile2());

        // Voer hier verdere verwerking uit op basis van de inhoud van het SQL-bestand.
        processSqlFile();
    }

    private void processSqlFile() {
    }

    public void processSqlFile() throws IOException {
        fileLoader.loadFile();
        // Voer hier verdere verwerking uit op basis van de inhoud van het SQL-bestand.
    }
}