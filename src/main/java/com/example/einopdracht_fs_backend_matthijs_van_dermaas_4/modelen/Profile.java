package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.List;


@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    //    Relation with User OneToOne.
    @OneToOne
    User user;
}
