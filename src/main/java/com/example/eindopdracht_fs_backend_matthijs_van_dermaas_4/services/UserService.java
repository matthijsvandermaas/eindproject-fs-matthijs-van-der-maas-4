package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //Constructor
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//user aanmaken
@Transactional
@JsonIgnore
public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
    try {
        User u = new User();
        u.setUsername(userDto.getUsername());
        u.setFirstName(userDto.getFirstName());
        u.setLastName(userDto.getLastName());
        u.setEmail(userDto.getEmail());
        u.setCompany(userDto.getCompany());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<String> roles = userDto.getRoles();
        u.setRoles(roles);

        userRepository.save(u); // Voeg de gebruiker toe aan de database

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error while creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

//user ophalen
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());
        return userDto;
    }

//alle users ophalen
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setCompany(user.getCompany());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}





