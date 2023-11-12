package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Profile;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProfileRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;


    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.profileRepository = profileRepository;
    }


    public List<ProfileDto> getAllProfile() {
        List<Profile> profile = profileRepository.findAll();
        List<ProfileDto> profileDto = new ArrayList<>();

        for (Profile p : profile) {
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);

            profileDto.add(pDto);
        }
        return profileDto;
    }

    private static void profileToProfileDto(Profile p, ProfileDto pDto) {
        pDto.setFirstName(p.getFirstName());
        pDto.setLastName(p.getLastName());
        pDto.setEmail(p.getEmail());
        pDto.setId(p.getId());
    }

    private void profileDtoToProfile(ProfileDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
        p.setId(pDto.getId());
    }

    public ProfileDto getProfile(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            Profile p = profile.get();
            ProfileDto pDto = new ProfileDto();
            profileToProfileDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ProfileDto createProfile(ProfileAndUserDto profileAndUserDto) {
        Profile profile = new Profile();
        profileAndUserDtoToProfile(profileAndUserDto, profile);


        Profile savedProfile = profileRepository.save(profile);

        // Create and return a ProfileDto based on the savedProfile
        ProfileDto savedProfileDto = new ProfileDto();
        profileToProfileDto(savedProfile, savedProfileDto);

        return savedProfileDto;
    }

    public String deleteProfile(@RequestBody Long id) {
        profileRepository.deleteById(id);

        return "Profile deleted";
    }
    private void profileAndUserDtoToProfile(ProfileAndUserDto pDto, Profile p) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
    }

}
