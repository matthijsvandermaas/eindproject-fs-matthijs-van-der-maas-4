package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
    List<FileDocument> findByProduct_Id(Long productId);
    FileDocument findByFileName(String fileName);
}
