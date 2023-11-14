package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
