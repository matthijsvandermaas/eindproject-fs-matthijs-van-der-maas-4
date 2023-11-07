package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UsersAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.RecordNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.Authority;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.util.RandomStringGenerator;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto;
        Optional<User> user = userRepository.findById(String.valueOf(Long.valueOf(username)));
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(String.valueOf(Long.valueOf(username)));
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(String.valueOf(Long.valueOf(username)));
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(String.valueOf(Long.valueOf(username)))) throw new RecordNotFoundException();
        User user = userRepository.findById(String.valueOf(Long.valueOf(username))).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(String.valueOf(Long.valueOf(username)))) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(String.valueOf(Long.valueOf(username))).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(String.valueOf(Long.valueOf(username)))) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(String.valueOf(Long.valueOf(username))).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(String.valueOf(Long.valueOf(username)))) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(String.valueOf(Long.valueOf(username))).get();
        Authority authorityToRemove = (Authority) user.getAuthorities().stream().filter((a) -> a.toString().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }


    public List<UserDto> getAllUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto createUserWithProfile(@Valid UsersAndUserDto profileDto) {
        User user = new User();
        user.setUsername(profileDto.getUsername());
        user.setPassword(profileDto.getPassword());
        user.setApikey(profileDto.getApikey());
        user.setRoles(profileDto.getRoles());
        user.setProfile(profileDto.getProfile());
        User newUser = userRepository.save(user);
        return fromUser(newUser);
    }
}
