package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;



import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.RoleDto;
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

import java.util.Optional;
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
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCompany(userDto.getCompany());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<Role> roles = new ArrayList<>();

        for (RoleDto roleDto : userDto.getRoles()) {
            Optional<Role> roleOptional = Optional.ofNullable((Role) roleRepository.findByName(roleDto.getName()));
            if (roleOptional.isPresent()) {
                roles.add(roleOptional.get());
            } else {
                throw new RoleNotFoundException("Role not found: " + roleDto.getName());
            }
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return UserDto.fromEntity(user);
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





