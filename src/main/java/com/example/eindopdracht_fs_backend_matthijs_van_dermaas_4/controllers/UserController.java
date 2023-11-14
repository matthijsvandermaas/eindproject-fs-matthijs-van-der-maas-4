package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    @JsonIgnore
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDto = userService.getAllUsers();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/createUser")
//    @PreAuthorize("hasAnyAuthority()")
    @JsonIgnore
    public ResponseEntity<UserDto> createUserWithUserDto(@Valid @RequestBody UserDto userDto) {
        User user = new User();
        userService.userDtoToUser(user, userDto);

        UserDto result = new UserDto();

        result.setUsername(userDto.getUsername());
        result.setFirstName(userDto.getFirstName());
        result.setLastName(userDto.getLastName());
        result.setEmail(userDto.getEmail());
        result.setCompany(userDto.getCompany());
        result.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (user.getPassword() != null) {
            result.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            System.err.println("Wachtwoord is leeg");
        }
        if (user.getRoles() != null) {
            List<Role> userRoles = new ArrayList<>();
            for (Role roleName : user.getRoles()) {
                Optional<Role> optionalRole = roleRepository.findById("ROLE_" + roleName.getName());
                optionalRole.ifPresent(userRoles::add);
            }
            result.setRoles(userRoles);
        }


        userRepository.save(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        System.err.println("Error" + e);
        return new ResponseEntity<>("Er is een interne fout opgetreden.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}













