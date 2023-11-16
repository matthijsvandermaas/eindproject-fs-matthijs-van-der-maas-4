package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;

import java.util.Base64;

public class FileDocumentDto {
    private final byte[] docFile;
    private Long id;
    private String fileName;
    private String url;

    // Constructors
    public FileDocumentDto(byte[] docFile) {
        this.docFile = docFile;
    }
    public FileDocumentDto(byte[] docFile, Long id, String fileName, String url) {
        this.docFile = docFile;
        this.id = id;
        this.fileName = fileName;
        this.url = url;
    }

    public FileDocumentDto(Long id, String fileName, String url, byte[] docFile) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.docFile = docFile;
    }

    public FileDocumentDto() {
        this.docFile = null;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //Methodes
    public static FileDocumentDto fromEntity(FileDocument fileDocument) {
        FileDocumentDto dto = new FileDocumentDto();
        dto.setId(fileDocument.getId());
        dto.setFileName(fileDocument.getFileName());
        dto.setFileData(Base64.getEncoder().encodeToString(fileDocument.getDocFile()));
        return dto;
    }

    private void setFileData(String s) {
        this.url = s;
    }

    public static FileDocument toEntity(FileDocumentDto dto) {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setId(dto.getId());
        fileDocument.setFileName(dto.getFileName());
        // Hier moet je logica toevoegen om Base64-string naar byte[] om te zetten
        return fileDocument;
    }

    // Extra methoden voor verwerken en bewerken
    public void processFile() {
        // Implementeer logica om het bestand te verwerken
    }

    public void editFileData(String newFileData) {
        // Implementeer logica om bestandsgegevens te bewerken
        // Hier moet je logica toevoegen om Base64-string naar byte[] om te zetten
    }



}
