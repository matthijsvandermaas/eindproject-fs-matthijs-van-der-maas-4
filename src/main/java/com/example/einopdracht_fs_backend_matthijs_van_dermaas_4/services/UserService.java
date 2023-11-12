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
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userToUserDto(user, userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
//TODO
    private static void userToUserDto(User u, UserDto uDto) {
//        uDto.setId(u.getId());
//        uDto.setUsername(u.getUsername());
//        uDto.setPassword(passwordEncoder.encode(u.getPassword()));
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
        uDto.setFirstName(u.getFirstName());
        uDto.setLastName(u.getLastName());
        uDto.setEmail(u.getEmail());
        uDto.setCompany(u.getCompany());

        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()) {
            roles.add(role.getRoleName());
        }
        uDto.setRoles(roles.toArray(new String[0]));
    }
    private void profileAndUserDtoToProfile(ProfileAndUserDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
        p.setCompany(pDto.getCompany());
    }

    private static void userDtoToUser(User u, UserDto uDto) {
        u.setUsername(uDto.getUsername());
        u.setFirstName(uDto.getFirstName());
        u.setLastName(uDto.getLastName());
        u.setEmail(uDto.getEmail());
        u.setCompany(uDto.getCompany());

    }


    public UserDto createUserWithProfile(ProfileAndUserDto profileDto) {

        // User gedeelte van de ProfileDTO
        UserDto userDto = new UserDto();
        userDto.setUsername(profileDto.getUsername());
        userDto.setFirstName(profileDto.getFirstName());
        userDto.setLastName(profileDto.getLastName());
        userDto.setEmail(profileDto.getEmail());
        userDto.setCompany(profileDto.getCompany());
        userDto.setPassword(passwordEncoder.encode(profileDto.getPassword()));
        System.out.println("UserDto: " + userDto.toString());

        User user = new User();
        if (profileDto.getRoles() != null) {
            user.setPassword(passwordEncoder.encode(profileDto.getPassword()));
            user.setUsername(userDto.getUsername());
//            user.setFirstName(userDto.getFirstName());
//            user.setLastName(userDto.getLastName());
//            user.setEmail(userDto.getEmail());
//            user.setCompany(userDto.getCompany());
            List<Role> userRoles = new ArrayList<>();
            for (String rolename : profileDto.getRoles()) {
                Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
                if (or.isPresent()) {
                    userRoles.add(or.get());
                 } else {
                System.out.println("Role not found: " + rolename);
            }

            }
            user.setRoles(userRoles);
        }
        System.out.println("UserDto: " + userDto.toString());
        System.out.println("User: " + user.toString());

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
        p.setUsername(pDto.getUsername());
        p.setPassword(pDto.getPassword());
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
        p.setCompany(pDto.getCompany());

    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}

