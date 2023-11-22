package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Authority;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.AuthorityKey;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> {
    List<Authority> findByUser(User user);

    List<Authority> findByAuthority(String authority);
}

