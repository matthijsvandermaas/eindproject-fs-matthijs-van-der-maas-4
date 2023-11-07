package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;



@Entity
@Table(name = "FileDocument")
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    @Lob
    private byte[] docFile;
    @Lob
    private byte[] docFile2;
    @Lob
    private byte[] docFile3;

    public FileDocument(String fileName, byte[] docFile, byte[] docFile3) {
        this.fileName = fileName;
        this.docFile = docFile;
        this.docFile3 = docFile3;
    }

    public FileDocument() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setDocFile(byte[] bytes) {
    }

    public void setFileName(String name) {
    }

    public Object getDocFile() {
        return docFile;
    }
}
