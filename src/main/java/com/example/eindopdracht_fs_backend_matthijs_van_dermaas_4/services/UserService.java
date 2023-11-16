package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @JsonIgnore
    public void createUser(UserDto userDto) throws RoleNotFoundException {
        User u = new User();
        u.setUsername(userDto.getUsername());
        u.setFirstName(userDto.getFirstName());
        u.setLastName(userDto.getLastName());
        u.setEmail(userDto.getEmail());
        u.setCompany(userDto.getCompany());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = userDto.getRolesAsObjects();
        User.setRoles(roles);

        // Encode password and save user
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(u);

    }


    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return null;
    }



    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(user);
    }

    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        return UserDto.fromEntity(user);
    }
}





