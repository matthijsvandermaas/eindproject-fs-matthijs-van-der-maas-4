package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;




import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/fileDocuments")
public class FileDocumentController {






//    @GetMapping("/{fileDocumentId}")



    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileDocument(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>("Bestand succesvol geüpload", HttpStatus.CREATED);
    }


}