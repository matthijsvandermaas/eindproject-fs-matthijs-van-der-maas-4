package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ProducentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "voer hier je voornaam in")
    private String firstName;

    @NotEmpty (message = "voer hier je achternaam in")
    private String lastName;

    @NotEmpty (message = "voer hier de voor en achternaam in van de eigenaar")
    private String owner;

    @NotEmpty(message = "voer hier de naam van de brouwerij in")
    private String nameBrewery;

    @NotEmpty (message = "voer hier de verkoop locatie in")
    private String saleLocation;

    private String street;

    private String houseNumber;

    private String zipcode;

    private String city;
    @NotEmpty (message = "voer hier de plaats in")
    private String brands;

    @NotEmpty (message = "voer hier je email in")
    @Email
    private String email;

    @NotEmpty(message = "voer hier je gebruikersnaam in")
    private String userName;

    @NotEmpty (message = "voer hier je wachtwoord in")
    private String password;
    @NotEmpty(message = "Roles must not be empty")
    @ElementCollection
    private List<String> roles = List.of("BREWER");

    public ProducentDto(Long id, String firstName, String lastName, String owner, String nameBrewery, String saleLocation, String street, String houseNumber, String zipcode, String city, String brands, String email, String userName, String password, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.owner = owner;
        this.nameBrewery = nameBrewery;
        this.saleLocation = saleLocation;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.brands = brands;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public ProducentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNameBrewery() {
        return nameBrewery;
    }

    public void setNameBrewery(String nameBrewery) {
        this.nameBrewery = nameBrewery;
    }

    public String getSaleLocation() {
        return saleLocation;
    }

    public void setSaleLocation(String saleLocation) {
        this.saleLocation = saleLocation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    public void setRole(List<String> roles) {
        this.roles = roles;
    }
}

