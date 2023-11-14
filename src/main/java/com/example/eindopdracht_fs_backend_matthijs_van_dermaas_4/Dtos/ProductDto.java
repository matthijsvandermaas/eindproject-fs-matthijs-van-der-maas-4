package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;


public class ProductDto {

    private Long id;
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;
    @NotEmpty(message = "Name brewer cannot be empty")
    private String nameBrewer;

    @NotEmpty(message = "Production location cannot be empty")
    private String productionLocation;
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotEmpty(message = "Alcohol cannot be empty")
    private Integer alcohol;
    private Integer ibu;
    @NotEmpty(message = "Color cannot be empty")
    private String color;
    @NotEmpty
    private String tast;
    @NotEmpty(message = "Volume cannot be empty")
    private String volume;


    public ProductDto(Long id, String productName, String nameBrewer, String productionLocation, String type, Integer alcohol, Integer ibu, String color, String tast, String volume, byte[] file, byte[] file2, String fileName, String fileName2) {
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

    public ProductDto() {

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