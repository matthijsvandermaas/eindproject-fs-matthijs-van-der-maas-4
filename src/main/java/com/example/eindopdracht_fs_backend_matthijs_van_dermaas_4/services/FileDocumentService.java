package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.Optional;

@Service
public class FileDocumentService {
    @Autowired
    private FileDocumentRepository fileDocumentRepository;

    public FileDocument saveFile(MultipartFile file) throws IOException {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(file.getOriginalFilename());
        fileDocument.setFileType(file.getContentType());
        fileDocument.setFileContent(file.getBytes());

        return fileDocumentRepository.save(fileDocument);
    }

    public FileDocument getFileById(Long id) {
        Optional<FileDocument> fileDocumentOptional = fileDocumentRepository.findById(id);
        return fileDocumentOptional.orElse(null);
    }
}
