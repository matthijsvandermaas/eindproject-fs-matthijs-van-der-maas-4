package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

public class ProducentDto {

    public String firstName;

    public String lastName;

    private String owner;

    public String nameBrewery;

    public String saleLocation;
    public String street;

    public String houseNumber;

    public String zipcode;

    public String city;

    public String brands;
    @Email
    public String email;

    public String userName;

    public String password;


    public String encode(String password) {
        return password;
    }

    public Set<Authority> Authority = new HashSet<>();

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

    public Set<com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority> getAuthority() {
        return Authority;
    }

    public void setAuthority(Set<com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority> authority) {
        Authority = authority;
    }
}
