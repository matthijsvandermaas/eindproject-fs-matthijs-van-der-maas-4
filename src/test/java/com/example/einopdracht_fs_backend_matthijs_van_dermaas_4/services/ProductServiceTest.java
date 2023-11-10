package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProductDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetProductByIdSuccess() {
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setProductName("Mock Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        ProductDto result = ProductService.getProduct(productId);

        verify(productRepository, times(1)).findById(productId);

        Assertions.assertNotNull(result, "Result should not be null");

        // Handmatige controle zonder assertEquals
        if (!productId.equals(result.getId())) {
            // Voeg deze afdrukken toe om te zien wat de werkelijke en verwachte ID zijn
            System.out.println("Expected ID: " + productId);
            System.out.println("Actual ID: " + result.getId());
            throw new AssertionError("ID mismatch");
        }

        if (!"Mock Product".equals(result.getProductName())) {
            throw new AssertionError("Product name mismatch");
        }
    }
    @Test
    void testGetProductByIdNotFound() {
        Long productId = 2L;

        // Mocking the behavior of productRepository.findById
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Calling the actual method and expecting an exception
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> ProductService.getProduct(productId));

        // Verifying that the expected methods were called
        Mockito.verify(productRepository, times(1)).findById(productId);

        // Asserting the exception message
        assertEquals("Property not found with ID: " + productId, exception.getMessage());
    }


    @Test
    void testCreateProduct() {
        // Mock input data
        ProductDto inputProductDto = new ProductDto();
        inputProductDto.setProductName("Test Product");
        inputProductDto.setNameProducer("Test Producer");

        // Mock saved product
        Product savedProduct = new Product();
        savedProduct.setId(1L);

        // Mock the save method
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        // Call the method under test
        ProductDto result = productService.createProduct(inputProductDto);

        // Verify that the save method was called once with the correct argument
        verify(productRepository, times(1)).save(Mockito.any(Product.class));

        // Verify that the returned ProductDto is not null
        assert result != null;


    }
    @Test
    void testUpdateProductSuccess() {
        Long productId = 1L;
        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setProductName("Updated Product");

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setProductName("Original Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(existingProduct);

        ProductDto result = productService.updateProduct(productId, updatedProductDto);

        verify(productRepository, Mockito.times(1)).findById(productId);
        verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));

        assertEquals(updatedProductDto.getProductName(), result.getProductName());
        // Voeg hier andere verwachtingen toe voor andere bijgewerkte velden indien nodig
    }

    @Test
    void testUpdateProductNotFound() {
        Long productId = 2L;
        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setProductName("Updated Product");

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () ->
                ProductService.updateProduct(productId, updatedProductDto));

        verify(productRepository, Mockito.times(1)).findById(productId);
        verify(productRepository, Mockito.never()).save(Mockito.any(Product.class));

        assertEquals("Property not found with ID: " + productId, exception.getMessage());
    }

    @Test
    void testGetAllData() {
        // Mock data
        List<Product> mockData = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Product 2");

        mockData.add(product1);
        mockData.add(product2);

        // Mock repository behavior
        when(productRepository.findAll()).thenReturn(mockData);

        // Call the method to test
        List<ProductDto> result = productService.getAllData();

        // Verify repository method was called
        verify(productRepository, times(1)).findAll();

        // Verify the result
        assertEquals(2, ((List<?>) result).size());

        // Check details for the first product
        assertEquals(1L, result.get(0).getId());
        assertEquals("Product 1", result.get(0).getProductName());

        // Check details for the second product
        assertEquals(2L, result.get(1).getId());
        assertEquals("Product 2", result.get(1).getProductName());
    }



}



