package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createWithProfile")
    public ResponseEntity<UserDto> createUserWithProfile(@Valid @RequestBody ProfileAndUserDto profileDto) {
        UserDto result = userService.createUserWithProfile(profileDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}

