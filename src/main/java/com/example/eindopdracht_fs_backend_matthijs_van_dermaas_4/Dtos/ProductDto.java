package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Product;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public class ProductDto {
    private Long id;

    @NotEmpty(message = "productName cannot be empty")
    private String productName;

    @NotEmpty(message = "nameBrewer cannot be empty")
    private String nameBrewer;

    private String productionLocation;

    @NotEmpty(message = "type cannot be empty")
    private String type;

    private Double alcohol;
    private Double ibu;

    @NotEmpty(message = "color cannot be empty")
    private String color;

    @NotEmpty(message = "tast cannot be empty")
    private String tast;

    private Double volume;
    private Set<FileDocumentDto> files;

    // Constructors
    public ProductDto() {}

    public ProductDto(Long id, String productName, String nameBrewer, String productionLocation, String type,
                      Double alcohol, Double ibu, String color, String tast, Double volume,
                      Set<FileDocumentDto> files) {
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
        this.files = files;
    }

    public static ProductDto fromEntity(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setNameBrewer(product.getNameBrewer());
        productDto.setProductionLocation(product.getProductionLocation());
        productDto.setType(product.getType());
        productDto.setAlcohol(product.getAlcohol());
        productDto.setIbu(product.getIbu());
        productDto.setColor(product.getColor());
        productDto.setTast(product.getTast());
        productDto.setVolume(product.getVolume());
        return productDto;
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setNameBrewer(productDto.getNameBrewer());
        product.setProductionLocation(productDto.getProductionLocation());
        product.setType(productDto.getType());
        product.setAlcohol(productDto.getAlcohol());
        product.setIbu(productDto.getIbu());
        product.setColor(productDto.getColor());
        product.setTast(productDto.getTast());
        product.setVolume(productDto.getVolume());
        return product;
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

    public Set<FileDocumentDto> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDocumentDto> files) {
        this.files = files;
    }
}

