package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.FileDocument;

import java.util.Date;

public class FileDocumentDto {

    private Long id;
    private String fileName;
    private String fileType;
    private Date uploadDate;

    // Constructors, getters, and setters

    public FileDocumentDto() {
        // Default constructor
    }

    public FileDocumentDto(Long id, String fileName, String fileType, Date uploadDate) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }

    public static FileDocumentDto fromEntity(FileDocument savedFile) {
        return new FileDocumentDto(
                savedFile.getId(),
                savedFile.getFileName(),
                savedFile.getFileType(),
                savedFile.getUploadDate()
        );
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
