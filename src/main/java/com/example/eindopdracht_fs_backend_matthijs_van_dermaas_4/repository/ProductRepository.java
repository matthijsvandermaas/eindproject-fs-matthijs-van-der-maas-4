package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
}
