package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;


@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "productname")
    @NotEmpty(message = "product name cannot be empty")
    private String productName;
    @Column(name = "namebrewer")
    private String nameBrewer;
    @Column(name = "productionlocation")
    private String productionLocation;
    @Column(name = "type")
    private String type;
    @Column(name = "alcohol")
    private Integer alcohol;
    @Column(name = "ibu")
    private Integer ibu;
    @Column(name = "color")
    private String color;
    @Column(name = "taste")
    private String tast;
    @Column(name = "volume")
    private String volume;

    @Lob
    @Column(name = "file")
    private byte[] file;
    @Column(name = "filename")
    private String fileName;
    @Lob
    @Column(name = "file2")
    private byte[] file2;
    @Column(name = "filename2")
    private String fileName2;

    public Product(Long id, String productName, String nameBrewer, String productionLocation, String type, Integer alcohol, Integer ibu, String color, String tast, String volume, byte[] file, byte[] file2, String fileName, String fileName2) {
        this.id = id;
        this.productName = productName;
        this.nameBrewer = nameBrewer;
        this.productionLocation = productionLocation;
        this.type = type;
        this.alcohol = alcohol;
        this.ibu = ibu;
        this.color = color;
        this.tast = tast;
        this.volume = volume;
        this.file = file;
        this.file2 = file2;
        this.fileName = fileName;
        this.fileName2 = fileName2;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNameBrewer() {
        return nameBrewer;
    }

    public void setNameBrewer(String nameBrewer) {
        this.nameBrewer = nameBrewer;
    }

    public String getProductionLocation() {
        return productionLocation;
    }

    public void setProductionLocation(String productionLocation) {
        this.productionLocation = productionLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

    public Integer getIbu() {
        return ibu;
    }

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTast() {
        return tast;
    }

    public void setTast(String tast) {
        this.tast = tast;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public byte[] getFile2() {
        return file2;
    }

    public void setFile2(byte[] file2) {
        this.file2 = file2;
    }

    public String getFileName() {
    return fileName;
    }

    public String getFileName2() {
    return fileName2;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

}
