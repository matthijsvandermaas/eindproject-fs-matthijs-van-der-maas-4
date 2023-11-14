package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}