package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "nameBrewer")
    private String nameBrewer;

    @Column(name = "productionLocation")
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
    public Product(Long id, String productName, String nameBrewer, String productionLocation, String type,
                   Double alcohol, Double ibu, String color, String tast, Double volume) {
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
    // Getters and setters

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

    public Double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Double alcohol) {
        if (alcohol >= 0.0 && alcohol <= 14.0) {
            this.alcohol = alcohol;
        } else {
            System.err.println("De waarde van het alcohol % is te laag (> 0.0) of te hoog (< 14.0)");
            setErrorMessage("De waarde van de alcohol % is te laag (> 0.0) of te hoog (< 120.0)");
            throw new IllegalArgumentException("Ongeldige waarde voor Alcohol %");
        }

    }

    public Double getIbu() {
        return ibu;
    }
        public void setIbu(Double ibu) {
            if (ibu == 0.0 || ibu >= 120.0) {
                this.ibu = ibu;
            } else {
                System.err.println("De waarde van de IBU is te laag (> 0.0) of te hoog (< 120.0)");
                setErrorMessage("De waarde van de IBU is te laag (> 0.0) of te hoog (< 120.0)");
                throw new IllegalArgumentException("Ongeldige waarde voor IBU");
            }
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

