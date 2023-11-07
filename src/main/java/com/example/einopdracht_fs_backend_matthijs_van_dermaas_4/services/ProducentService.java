package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.exceptions.IdNotFoundException;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProducentService {

    private static ProducentRepository producentRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public ProducentService(ProducentRepository producentRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        ProducentService.producentRepository = producentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public List<ProducentDto> getAllProfile() {
        List<Producent> profile = producentRepository.findAll();
        List<ProducentDto> producentDto = new ArrayList<>();

        for (Producent p : profile) {
            ProducentDto pDto = new ProducentDto();
            producentToProducentDto(p, pDto);

            producentDto.add(pDto);
        }
        return producentDto;
    }
    private void producentDtoToProducent(ProducentDto pDto, Producent p) {
        pDto.setFirstName(p.getFirstName());
        pDto.setLastName(p.getLastName());
        pDto.setOwner(p.getOwner());
        pDto.setNameBrewery(p.getNameBrewery());
        pDto.setSaleLocation(p.getSaleLocation());
        pDto.setStreet(p.getStreet());
        pDto.setHouseNumber(p.getHouseNumber());
        pDto.setZipcode(p.getZipcode());
        pDto.setCity(p.getCity());
        pDto.setBrands(p.getBrands());
        pDto.setEmail(p.getEmail());
        pDto.setUserName(p.getUserName());
        pDto.setPassword(p.getPassword());
    }
    private static void producentToProducentDto(Producent p, ProducentDto pDto) {
        p.setFirstName(pDto.getFirstName());
        p.setLastName(pDto.getLastName());
        p.setOwner(pDto.getOwner());
        p.setNameBrewery(pDto.getNameBrewery());
        p.setSaleLocation(pDto.getSaleLocation());
        p.setStreet(pDto.getStreet());
        p.setHouseNumber(pDto.getHouseNumber());
        p.setZipcode(pDto.getZipcode());
        p.setCity(pDto.getCity());
        p.setBrands(pDto.getBrands());
        p.setEmail(pDto.getEmail());
        p.setUserName(pDto.getUserName());
        p.setPassword(pDto.getPassword());
    }


    public static ProducentDto getProducent(Long id) {
        Optional<Producent> producent = producentRepository.findById(id);
        if (producent.isPresent()) {
            Producent p = producent.get();
            ProducentDto pDto = new ProducentDto();
            producentToProducentDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
        }
    }

    public ProducentDto createProducent(ProducentDto producentDto) {
        Producent producent = new Producent();
        producentDtoToProducent(producentDto, producent);

        if (producentDto.getRoles() != null) {
            List<Role> userRoles = new ArrayList<>();
            for (String rolename : producentDto.getRoles()) {
                Optional<Role> role = roleRepository.findById("ROLE_" + rolename);
                role.ifPresent(userRoles::add);
            }
        }

        Producent savedProducent = producentRepository.save(producent);

        // Create and return a ProfileDto based on the savedProducent
        ProducentDto savedProducentDto = new ProducentDto();
        producentToProducentDto(savedProducent, savedProducentDto);

        return savedProducentDto;
    }

    public String deleteProducent(@RequestBody Long id) {
        producentRepository.deleteById(id);

        return "Brewer deleted";
    }
}