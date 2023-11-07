package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "producenten")
public class Producent {
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

    @OneToMany
    private Set<Authority> authorities = new HashSet<>();
    public Producent() {
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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
