package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "productname")
    private String productName;

    @Column(name = "namebrewer")
    private String nameBrewer;

    @Column(name = "productionlocation")
    private String productionLocation;

    @Column(name = "type")
    private String type;

    @Column(name = "alcohol")
    private Double alcohol;

    @Column(name = "ibu")
    private Double ibu;

    @Column(name = "color")
    private String color;

    @Column(name = "tast")
    private String tast;

    @Column(name = "volume")
    private Double volume;

    private String errorMessage;


    // Constructor
    public Product(String productName, String nameBrewer, String productionLocation, String type,
                   Double alcohol, Double ibu, String color, String tast, Double volume) {
        this.productName = productName;
        this.nameBrewer = nameBrewer;
        this.productionLocation = productionLocation;
        this.type = type;
        this.setAlcohol(alcohol);
        this.setIbu(ibu);
        this.color = color;
        this.tast = tast;
        this.volume = volume;
    }
       public Product() {
       }
    // Getters and setters
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

    public Double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Double alcohol) {
               this.alcohol = alcohol;
    }

    public Double getIbu() {
        return ibu;
    }
        public void setIbu(Double ibu) {
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

