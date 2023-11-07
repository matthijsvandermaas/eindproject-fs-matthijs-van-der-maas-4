package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
