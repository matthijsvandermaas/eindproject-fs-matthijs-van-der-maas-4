package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UsersAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.BadRequestException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dDto = userService.getAllUsers();
        return new ResponseEntity<>(dDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUserWithProfile(@Valid @RequestBody UsersAndUserDto profileDto) {
        UserDto result = userService.createUserWithProfile(profileDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}