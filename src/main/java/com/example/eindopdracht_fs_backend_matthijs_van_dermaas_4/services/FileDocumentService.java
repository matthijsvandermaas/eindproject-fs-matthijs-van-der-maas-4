package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.FileDocumentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDocumentService {

    private final FileDocumentRepository fileDocumentRepository;

    @Autowired
    public FileDocumentService(FileDocumentRepository fileDocumentRepository) {
        this.fileDocumentRepository = fileDocumentRepository;
    }

    public FileDocumentDto uploadFile(MultipartFile file) {
        try {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(file.getOriginalFilename());
            fileDocument.setFileType(file.getContentType());
            fileDocument.setFileContent(file.getBytes());

            FileDocument savedFile = fileDocumentRepository.save(fileDocument);

            return FileDocumentDto.fromEntity(savedFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public FileDocumentDto getFileById(Long fileId) {
        FileDocument fileDocument = fileDocumentRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + fileId));

        return FileDocumentDto.fromEntity(fileDocument);
    }


}
