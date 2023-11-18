package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;




import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.FileUpload.FileUploadResponse;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/fileDocuments")
public class FileDocumentController {


    @Autowired
    private FileDocumentService fileDocumentService;

    @Autowired
    private FileDocumentRepository fileDocumentRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileDocuments(@RequestParam("files") List<MultipartFile> files) {
        try {
            if (files.isEmpty()) {
                return new ResponseEntity<>("De geüploade bestanden zijn leeg", HttpStatus.BAD_REQUEST);
            }

            for (MultipartFile file : files) {
                // Genereer een unieke bestandsnaam
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

                // Pad waar de bestanden moeten worden opgeslagen (pas dit aan op basis van je behoeften)
                Path uploadPath = Paths.get("uploads");

                // Controleer of de map bestaat, anders maak deze aan
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Volledig pad naar het opgeslagen bestand
                Path filePath = uploadPath.resolve(fileName);

                // Kopieer het bestand naar het opgegeven pad
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return new ResponseEntity<>("Bestanden succesvol geüpload", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Fout bij het uploaden van de bestanden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFile/{id}")
    public ResponseEntity<FileUploadResponse> getFileById(@PathVariable Long id) {
        try {
            // Haal het bestand op basis van de ID
            FileDocument fileDocument = fileDocumentService.getFileById(id);

            // Controleer of het bestand bestaat
            if (fileDocument != null) {
                // Construeer een FileUploadResponse-object met de relevante informatie
                FileUploadResponse response = new FileUploadResponse(
                        fileDocument.getFileName(),
                        fileDocument.getFileType(),
                        "URL naar het bestand, bijvoorbeeld /fileDocuments/download/" + id
                );

                // Retourneer het FileUploadResponse-object
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}