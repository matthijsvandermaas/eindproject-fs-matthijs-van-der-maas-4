package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.FileDocumentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.DocFileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDocumentService {

    private final DocFileRepository docFileRepository;

    @Autowired
    public FileDocumentService(DocFileRepository docFileRepository) {
        this.docFileRepository = docFileRepository;
    }

    public FileDocumentDto getFileDocumentById(Long fileDocumentId) {
        FileDocument fileDocument = docFileRepository.findById(fileDocumentId)
                .orElseThrow(() -> new RuntimeException("FileDocument not found with id: " + fileDocumentId));

        return FileDocumentDto.fromEntity(fileDocument);
    }

    public List<FileDocumentDto> getAllFileDocuments() {
        List<FileDocument> fileDocuments = docFileRepository.findAll();
        return fileDocuments.stream()
                .map(FileDocumentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public FileDocumentDto createFileDocument(FileDocumentDto fileDocumentDto) {
        FileDocument fileDocument = FileDocumentDto.toEntity(fileDocumentDto);
        FileDocument savedFileDocument = docFileRepository.save(fileDocument);
        return FileDocumentDto.fromEntity(savedFileDocument);
    }

    public FileDocumentDto updateFileDocument(Long fileDocumentId, FileDocumentDto fileDocumentDto) {
        FileDocument existingFileDocument = docFileRepository.findById(fileDocumentId)
                .orElseThrow(() -> new RuntimeException("FileDocument not found with id: " + fileDocumentId));

        FileDocument updatedFileDocument = FileDocumentDto.toEntity(fileDocumentDto);
        updatedFileDocument.setId(existingFileDocument.getId());

        FileDocument savedFileDocument = docFileRepository.save(updatedFileDocument);
        return FileDocumentDto.fromEntity(savedFileDocument);
    }

    public void deleteFileDocument(Long fileDocumentId) {
        FileDocument fileDocument = docFileRepository.findById(fileDocumentId)
                .orElseThrow(() -> new RuntimeException("FileDocument not found with id: " + fileDocumentId));

        docFileRepository.delete(fileDocument);
    }

    public FileDocumentDto uploadFileDocument(MultipartFile fileDocumentDto) {
        FileDocument fileDocument = FileDocumentDto.toEntity((FileDocumentDto) fileDocumentDto);
        FileDocument savedFileDocument = docFileRepository.save(fileDocument);
        return FileDocumentDto.fromEntity(savedFileDocument);
    }

    public FileDocument singleFileDownload(String fileName, HttpServletRequest request) {
        return docFileRepository.findByFileName(fileName);
    }
}
