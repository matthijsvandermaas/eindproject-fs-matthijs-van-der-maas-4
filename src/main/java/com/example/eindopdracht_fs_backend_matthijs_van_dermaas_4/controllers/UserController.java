package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService.userDtoToUser;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
        private final UserService userService;

        public UserController(PasswordEncoder passwordEncoder, UserService userService) {
            this.passwordEncoder = passwordEncoder;
            this.userService = userService;
        }


        @GetMapping
        @JsonIgnore
        public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> dDto = userService.getAllUsers();
            return new ResponseEntity<>(dDto, HttpStatus.OK);
        }

        @PostMapping("/createUser")
        @JsonIgnore
        public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
            User user = new User();
            userDtoToUser(user, userDto);
            UserDto result = userService.createUserWithUserDto(user, passwordEncoder);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        System.err.println("Error" + e);

        return new ResponseEntity<>("Er is een interne fout opgetreden.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }













