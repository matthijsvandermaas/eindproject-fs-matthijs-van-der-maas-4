package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducentRepository extends JpaRepository<Producent, Long> {
    Optional<Producent> findByUserName(String username);


}

