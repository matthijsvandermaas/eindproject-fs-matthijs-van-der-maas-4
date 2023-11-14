//package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos;
//
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//
//
//public class ProfileDto {
//    @Id
//    private Long id;
//    @NotEmpty(message = "First name cannot be empty")
//    private String firstName;
//    @NotEmpty(message = "Last name cannot be empty")
//    private String lastName;
//    @NotEmpty(message = "Email cannot be empty")
//    @Email(message = "This needs to be an email address")
//    private String email;
//    @NotEmpty(message = "THis needs to be een company name")
//    private String company;
//    private String[] roles;
//
//    public ProfileDto(Long id, String firstName, String lastName, String email, String company , String[] roles) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.company =  company;
//        this.roles = roles;
//    }
//
//    public ProfileDto() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//}