package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;

@Entity
@Table(name = "file_document")
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "doc_file")
    private byte[] docFile;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // Constructor

    public FileDocument() {
    }

    public FileDocument(String fileName, byte[] docFile) {
        this.fileName = fileName;
        this.docFile = docFile;
    }
    // Getters en setters

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

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }


    // Je kunt deze methode toevoegen om gemakkelijk een FileDocument aan een Product toe te voegen
    public void addToProduct(Product product) {
        this.product = product;
        product.getFiles().add(this);
    }

    // Andere methoden en logica indien nodig
}
