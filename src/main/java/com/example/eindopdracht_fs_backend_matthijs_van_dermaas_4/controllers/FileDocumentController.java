package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.FileDocumentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileDocuments")
public class FileDocumentController {

    private final FileDocumentService fileDocumentService;

    @Autowired
    public FileDocumentController(FileDocumentService fileDocumentService) {
        this.fileDocumentService = fileDocumentService;
    }

    @GetMapping("/{fileDocumentId}")
    public ResponseEntity<FileDocumentDto> getFileDocumentById(@PathVariable Long fileDocumentId) {
        FileDocumentDto fileDocumentDto = fileDocumentService.getFileDocumentById(fileDocumentId);
        return new ResponseEntity<>(fileDocumentDto, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDocumentDto> uploadFileDocument(@RequestBody FileDocumentDto fileDocumentDto) {
        FileDocumentDto createdFileDocumentDto = fileDocumentService.uploadFileDocument((MultipartFile) fileDocumentDto);
        return new ResponseEntity<>(createdFileDocumentDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{fileDocumentId}")
    public ResponseEntity<?> deleteFileDocument(@PathVariable Long fileDocumentId) {
        fileDocumentService.deleteFileDocument(fileDocumentId);
        return new ResponseEntity<>("FileDocument deleted successfully", HttpStatus.OK);
    }
}
