package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, String> {

    Authority findByAuthority(String authority);


}
