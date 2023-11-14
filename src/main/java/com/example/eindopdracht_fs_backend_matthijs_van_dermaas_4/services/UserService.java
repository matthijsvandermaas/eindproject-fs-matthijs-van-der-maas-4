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
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }


    //    public List<UserDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        List<UserDto> userDtoList = new ArrayList<>();
//        for (User user : users) {
//            UserDto userDto = new UserDto();
//            userToUserDto(user, userDto);
//            userDtoList.add(userDto);
//        }
//        return userDtoList;
//    }

    public static void userDtoToUser(User user, UserDto userDto) {
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCompany(userDto.getCompany());
    }

    private static void userToUserDto(UserDto userDto, User user) {
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());
        List<Role> roleList = user.getRoles();

    }

    public UserDto createUserWithUserDto(User user, PasswordEncoder encode) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());

        if (user.getPassword() != null) {
            userDto.setPassword(encode.encode(user.getPassword()));
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

    }


