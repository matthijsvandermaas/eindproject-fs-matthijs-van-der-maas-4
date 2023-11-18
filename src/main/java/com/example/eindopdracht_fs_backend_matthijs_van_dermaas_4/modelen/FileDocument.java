package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.FileDocumentRepository;
import jakarta.persistence.*;

import java.util.Date;
import java.util.logging.Logger;

@Entity
@Table(name = "file_document")
public class FileDocument {
    private static final Logger LOGGER = Logger.getLogger(FileDocument.class.getName());
    @Id
    @GeneratedValue
    @Column(name = "file_document_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;

    private String fileType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    private String filePath;

    public FileDocument() {
        // Default constructor
    }
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // Constructor


    public FileDocument(String fileName, byte[] fileContent, String fileType, Date uploadDate) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }


    // Getters en setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
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


    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;


    }

    // methodes

    public static FileDocument getFileDocumentById(Long fileDocumentId, FileDocumentRepository fileDocumentRepository) {
        LOGGER.info("Fetching FileDocument by ID: " + fileDocumentId);
        return fileDocumentRepository.findById(fileDocumentId).orElse(null);
    }
    public void addToProduct(Product product) {
        this.product = product;
        product.getFiles().add(this);
    }

}
