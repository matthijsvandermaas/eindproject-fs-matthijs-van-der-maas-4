package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "producten")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productName;

    @NotBlank
    private String nameProducer;

    @NotEmpty
    private String type;

    @NotBlank
    @Max(15)
    private Double percentage;

    @NotEmpty
    private String color;

    @NotEmpty
    private String tast;


    @Max(1000)
    private Double volume;

    @NotBlank
    private String producentLocation;

    @NotEmpty
    private String DocFile;
    @NotEmpty
    private String DocFile2;

    @NotEmpty(message = "Roles must not be empty")
    @ElementCollection
    private List<String> roles = List.of("BREWER");

    public Product(Long id, String productName, String nameProducer, String type, Double percentage, String color, String tast, Double volume, String producentLocation, String docFile, String docFile2, List<String> roles) {
        this.id = id;
        this.productName = productName;
        this.nameProducer = nameProducer;
        this.type = type;
        this.percentage = percentage;
        this.color = color;
        this.tast = tast;
        this.volume = volume;
        this.producentLocation = producentLocation;
        DocFile = docFile;
        DocFile2 = docFile2;
        this.roles = roles;
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

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
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

    public String getProducentLocation() {
        return producentLocation;
    }

    public void setProducentLocation(String producentLocation) {
        this.producentLocation = producentLocation;
    }

    public String getDocFile() {
        return DocFile;
    }

    public void setDocFile(String docFile) {
        DocFile = docFile;
    }

    public String getDocFile2() {
        return DocFile2;
    }

    public void setDocFile2(String docFile2) {
        DocFile2 = docFile2;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}



