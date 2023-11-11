package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Profile;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProfileRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
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
    private final ProfileRepository profileRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ProfileRepository profileRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);

            userDtos.add(uDto);
        }
        return userDtos;
    }

    private static void userToUserDto(User u, UserDto uDto) {
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()) {
            roles.add(role.getRoleName());
        }
        uDto.setRoles(roles.toArray(new String[0]));
    }

    private static void userDtoToUser(User u, UserDto uDto) {
        u.setUsername(uDto.getUsername());
        u.setPassword(uDto.getPassword());
    }


    public UserDto createUserWithProfile(ProfileAndUserDto profileDto) {

        // User gedeelte van de ProfileDTO
        UserDto userDto = new UserDto();
        userDto.setUsername(profileDto.getUsername());
        userDto.setPassword(passwordEncoder.encode(profileDto.getPassword()));

        User user = new User();
        if (profileDto.getRoles() != null) {
            List<Role> userRoles = new ArrayList<>();
            for (String rolename : profileDto.getRoles()) {
                Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
                if (or.isPresent()) {
                    userRoles.add(or.get());
                }
            }


            userDtoToUser(user, userDto);
            user.setRoles(userRoles);
        }

        Profile profile = new Profile();
        profileDtoToProfile(profileDto, profile);


        profile.setUser(user);
        user.setProfile(profile);

        userRepository.save(user);
        profileRepository.save(profile);


        UserDto savedUserDto = new UserDto();
        userToUserDto(user, savedUserDto);

        return savedUserDto;
    }


    private void profileDtoToProfile(ProfileAndUserDto pDto, Profile p) {
//        p.setUsername(pDto.getUsername());
//        p.setPassword(pDto.getPassword());
//        p.setConfirmPassword(pDto.getConfirmPassword());
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }
}