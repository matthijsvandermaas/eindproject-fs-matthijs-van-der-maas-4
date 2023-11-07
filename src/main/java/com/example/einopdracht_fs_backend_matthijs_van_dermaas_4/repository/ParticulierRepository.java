package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticulierRepository extends JpaRepository<Particulier, Long> {
    Optional<Particulier> findByUserName(String username);


}

