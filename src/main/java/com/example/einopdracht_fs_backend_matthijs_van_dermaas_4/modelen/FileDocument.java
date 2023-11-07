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
        private byte[] docFile2;

    public FileDocument(Long id, String fileName, byte[] docFile, byte[] docFile2) {
        this.id = id;
        this.fileName = fileName;
        this.docFile = docFile;
        this.docFile2 = docFile2;
    }

    public FileDocument() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setDocFile(byte[] bytes) {
        this.docFile = bytes;
    }
    public void setDocFile2(byte[] bytes) {
        this.docFile2 = bytes;
    }

    public void setFileName(String name) {
    }

    public Object getDocFile() {
        return docFile;
    }
    public Object getDocFile2() {
        return docFile;
    }
}
