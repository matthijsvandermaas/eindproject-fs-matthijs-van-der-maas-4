package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ParticulierDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Particulier;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ParticulierRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticulierService {

    private static ParticulierRepository particulierRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public ParticulierService(ParticulierRepository particulierRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        ParticulierService.particulierRepository = particulierRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public List<ParticulierDto> getAllProfile() {
        List<Particulier> profile = particulierRepository.findAll();
        List<ParticulierDto> particulierDto = new ArrayList<>();

        for (Particulier p : profile) {
            ParticulierDto pDto = new ParticulierDto();
            particulierToParticulierDto(p, pDto);

            particulierDto.add(pDto);
        }
        return particulierDto;
    }
    private void particulierDtoToParticulier(ParticulierDto pDto, Particulier p) {
        pDto.setFirstName(p.getFirstName());
        pDto.setLastName(p.getLastName());
        pDto.setEmail(p.getEmail());
        pDto.setUserName(p.getUserName());
        pDto.setPassword(p.getPassword());
    }
    private static void particulierToParticulierDto(Particulier p, ParticulierDto pDto) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setEmail(pDto.getEmail());
        p.setUserName(pDto.getUserName());
        p.setPassword(pDto.getPassword());
    }


    public static ParticulierDto getParticulier(Long id) {
        Optional<Particulier> particulier = particulierRepository.findById(id);
        if (particulier.isPresent()) {
            Particulier p = particulier.get();
            ParticulierDto pDto = new ParticulierDto();
            particulierToParticulierDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ParticulierDto createParticulier(ParticulierDto particulierDto) {
        Particulier particulier = new Particulier();
        particulierDtoToParticulier(particulierDto, particulier);

        if (particulierDto.getRoles() != null) {
            List<Role> userRoles = new ArrayList<>();
            for (String rolename : particulierDto.getRoles()) {
                Optional<Role> role = roleRepository.findById("ROLE_" + rolename);
                role.ifPresent(userRoles::add);
            }
        }

        Particulier savedParticulier = particulierRepository.save(particulier);

        // Create and return a ProfileDto based on the savedParticulier
        ParticulierDto savedParticulierDto = new ParticulierDto();
        particulierToParticulierDto(savedParticulier, savedParticulierDto);

        return savedParticulierDto;
    }

    public String deleteParticulier(@RequestBody Long id) {
        particulierRepository.deleteById(id);

        return "User deleted";
    }
}