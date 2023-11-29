package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RoleNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
//create user
@PostMapping("/createUser")
@JsonIgnore
public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) throws RoleNotFoundException {
    try {
        userService.createUser(userDto);
        List<String> roles = userDto.getRoles();
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error while creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    //get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            List<UserDto> userDtos = users.stream()
                    .map(UserDto::fromEntity)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get user by username
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String username) throws RoleNotFoundException {
        logger.info("GET request received for username: {}", username);
        try {
            UserDto userDto = userService.getUserByUsername(username);
            if (userDto != null) {
                logger.info("User found for username: {}", username);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                logger.info("User not found for username: {}", username);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            logger.error("Error while processing the request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserDto> deleteProfile(@PathVariable String username) {
        try {
            userService.deleteProfile(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
