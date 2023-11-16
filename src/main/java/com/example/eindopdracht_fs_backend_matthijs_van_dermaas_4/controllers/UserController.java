package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RoleNotFoundException;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/createUser")
    @JsonIgnore
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) throws RoleNotFoundException {
        userService.createUser(userDto);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws RoleNotFoundException {
        try {
            UserDto userDto = userService.getUserById(id);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid userId format", HttpStatus.BAD_REQUEST);
        }
    }




    @DeleteMapping("/{deleteUserId}")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonIgnore
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


}
