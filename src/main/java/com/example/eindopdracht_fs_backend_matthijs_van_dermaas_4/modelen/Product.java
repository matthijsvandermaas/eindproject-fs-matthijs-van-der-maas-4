package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


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


}
