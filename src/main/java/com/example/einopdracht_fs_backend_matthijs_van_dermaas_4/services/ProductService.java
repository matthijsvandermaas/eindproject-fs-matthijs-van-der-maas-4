package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        ProductService.productRepository = productRepository;
    }


    public List<ProductDto> getAllData() {
        List<Product> data = productRepository.findAll();
        List<ProductDto> productDto = new ArrayList<>();

        for (Product p : data) {
            ProductDto pDto = new ProductDto();
            productToProductDto(p, pDto);

            productDto.add(pDto);
        }
        return productDto;
    }
    private void productDtoToProduct(ProductDto pDto, Product p) {
        pDto.setProductName(p.getProductName());
        pDto.setNameProducer(p.getNameProducer());
        pDto.setType(p.getType());
        pDto.setPercentage(p.getPercentage());
        pDto.setColor(p.getColor());
        pDto.setTast(p.getTast());
        pDto.setVolume(p.getVolume());
        pDto.setProducentLocation(p.getProducentLocation());
        pDto.setDocFile(p.getDocFile());
        pDto.setDocFile2(p.getDocFile2());


    }
    private static void productToProductDto(Product p, ProductDto pDto) {
        p.setProductName(pDto.getProductName());
        p.setNameProducer(pDto.getNameProducer());
        p.setType(pDto.getType());
        p.setPercentage(pDto.getPercentage());
        p.setColor(pDto.getColor());
        p.setTast(pDto.getTast());
        p.setVolume(pDto.getVolume());
        p.setProducentLocation(pDto.getProducentLocation());
        p.setDocFile(pDto.getDocFile());
        p.setDocFile2(pDto.getDocFile2());
    }


    public static ProductDto getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            ProductDto pDto = new ProductDto();
            productToProductDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        productDtoToProduct(productDto, product);


        Product savedProduct= productRepository.save(product);

        // Create and return a ProductDto based on the savedProduct
        ProductDto savedProductDto = new ProductDto();
        productToProductDto(savedProduct, savedProductDto);

       return savedProductDto;
}

    public String deleteProduct(@RequestBody Long id) {
        productRepository.deleteById(id);

        return "product deleted";
    }

    public static ProductRepository getProductRepository() {
        return productRepository;
    }

    public static void setProductRepository(ProductRepository productRepository) {
        ProductService.productRepository = productRepository;
    }

    public static ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            productToProductDto(p, productDto);
            productRepository.save(p);
            return productDto;
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

}