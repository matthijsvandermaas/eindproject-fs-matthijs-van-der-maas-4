package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    public UserService(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }
    public void userDtoToUser(User user, UserDto userDto) {
        System.err.println("Copying data from UserDto to User...");
        System.err.println("Username: " + userDto.getUsername());
        System.err.println("FirstName: " + userDto.getFirstName());
        System.err.println("LastName: " + userDto.getLastName());

        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCompany(userDto.getCompany());

        if (userDto.getPassword() != null) {
            user.setPassword(encoder.encode(userDto.getPassword()));
        } else {
            System.err.println("Password is null");
        }

        if (userDto.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (String roleName : userDto.getRoles()) {
                roles.add(new Role(roleName));
            }
            user.setRoles(roles);
        }
    }

    private static void userToUserDto(UserDto userDto, User user) {
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());

        List<Role> roleList = user.getRoles();
        if (user.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (Role roleName : user.getRoles()) {
                roles.add(new Role(roleName));
            }
            userDto.setRoles(roles);
        }

    }

    public UserDto createUserWithUserDto(User user, PasswordEncoder encode, String firstName) {
        UserDto userDto = new UserDto();
        userDtoToUser(user, userDto);

        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());

        System.out.println("UserDto: " + userDto.toString());
        System.out.println(user.getPassword());
        System.out.println(userDto.getPassword());
        System.out.println(userDto.getUsername());
        System.out.println(userDto.getFirstName());
        System.out.println(userDto.getLastName());
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getCompany());

        if (user.getPassword() != null) {
            userDto.setPassword(encoder.encode(user.getPassword()));
        } else {
            System.err.println("Password is null");
        }
        if (user.getRoles() != null) {
            List<Role> userRoles = new ArrayList<>();
            for (Role roleName : user.getRoles()) {
                Optional<Role> optionalRole = roleRepository.findById("ROLE_" + roleName.getName());
                optionalRole.ifPresent(userRoles::add);
            }
            userDto.setRoles(userRoles);
            System.out.println("UserDto: " + userDto.toString());
        }

        userRepository.save(user);
        return userDto;
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userToUserDto(userDto, user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public void userDtoToUser() {
    }
}


