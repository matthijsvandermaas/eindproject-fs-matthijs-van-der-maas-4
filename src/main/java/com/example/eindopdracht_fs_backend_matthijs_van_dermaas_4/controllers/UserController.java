package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RoleNotFoundException;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
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

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
//create user
    @PostMapping(value = "/createUser")
    @JsonIgnore
   public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) throws RoleNotFoundException {
        try {
            userService.createUser(userDto);
        List<String> roles = userDto.getRoles();
        System.out.println("Received user data: " + userDto.toString());
        System.out.println("Received roles: " + roles);
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

    //get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws RoleNotFoundException {
        try {
            UserDto userDto = userService.getUserById(id);
            if (userDto != null) {
                System.out.println("Request received for user id: " + id);
                return new ResponseEntity<>(userDto, HttpStatus.OK);

            } else {
                System.out.println("User not found");
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
        System.out.println("Request received for user id: " + id);
            return new ResponseEntity<>("Invalid userId format", HttpStatus.BAD_REQUEST);
        }
    }

}
