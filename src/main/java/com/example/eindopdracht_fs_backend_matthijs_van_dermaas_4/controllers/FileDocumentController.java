package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.FileDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileDocuments")
public class FileDocumentController {

    private final FileDocument fileDocumentService;
    private com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument fileDocument;

    @Autowired
    public FileDocumentController(FileDocument fileDocumentService) {
        this.fileDocumentService = fileDocumentService;
    }

    @GetMapping("/{fileDocumentId}")
    public ResponseEntity<com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument> getFileDocumentById(@PathVariable Long fileDocumentId) {
        com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument fileDocument = fileDocumentService.getFileDocumentById(fileDocumentId);
        return new ResponseEntity<>(fileDocument, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileDocument(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>("Bestand succesvol geüpload", HttpStatus.CREATED);    }

}
