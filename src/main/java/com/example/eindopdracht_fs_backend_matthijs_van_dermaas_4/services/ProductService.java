package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

}