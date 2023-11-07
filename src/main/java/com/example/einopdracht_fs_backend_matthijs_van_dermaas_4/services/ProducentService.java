package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducentService {

    private final ProducentRepository producentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ProducentService(ProducentRepository producentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.producentRepository = producentRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public String createProducent(ProducentDto producentDto) {
        // Het wachtwoord hashen voordat het wordt opgeslagen
        String hashedPassword = passwordEncoder.encode(producentDto.getPassword());

        // Een nieuwe Producent maken en gegevens instellen
        Producent newProducent = new Producent();
        newProducent.setFirstName(producentDto.getFirstName());
        newProducent.setLastName(producentDto.getLastName());
        newProducent.setOwner(producentDto.getOwner());
        newProducent.setNameBrewery(producentDto.getNameBrewery());
        newProducent.setSaleLocation(producentDto.getSaleLocation());
        newProducent.setStreet(producentDto.getStreet());
        newProducent.setHouseNumber(producentDto.getHouseNumber());
        newProducent.setZipcode(producentDto.getZipcode());
        newProducent.setCity(producentDto.getCity());
        newProducent.setBrands(producentDto.getBrands());
        newProducent.setEmail(producentDto.getEmail());
        newProducent.setUserName(producentDto.getUserName());
        newProducent.setPassword(hashedPassword);
        newProducent.setAuthority(producentDto.getAuthority());

        producentRepository.save(newProducent);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! Producent ID: " + newProducent.getId();
    }

    public List<Producent> getAllProducenten() {
        return producentRepository.findAll();
    }

    public Producent getProducentById(Long id) {
        Optional<Producent> producentOptional = producentRepository.findById(id);
        return producentOptional.orElse(null);
    }

    public void updateProducent(Long id, ProducentDto producentDto) {
        Optional<Producent> existingProducentOptional = producentRepository.findById(id);
        if (existingProducentOptional.isPresent()) {
            Producent existingProducent = existingProducentOptional.get();
            existingProducent.setFirstName(producentDto.getFirstName());
            existingProducent.setLastName(producentDto.getLastName());
            existingProducent.setOwner(producentDto.getOwner());
            existingProducent.setNameBrewery(producentDto.getNameBrewery());
            existingProducent.setSaleLocation(producentDto.getSaleLocation());
            existingProducent.setStreet(producentDto.getStreet());
            existingProducent.setHouseNumber(producentDto.getHouseNumber());
            existingProducent.setZipcode(producentDto.getZipcode());
            existingProducent.setCity(producentDto.getCity());
            existingProducent.setBrands(producentDto.getBrands());
            existingProducent.setEmail(producentDto.getEmail());
            existingProducent.setUserName(producentDto.getUserName());

            producentRepository.save(existingProducent);
        } else {
            // Of je kunt een exceptie gooien als de producent niet wordt gevonden
            throw new IllegalArgumentException("Producent met ID " + id + " niet gevonden");
        }
    }

    public void deleteProducent(Long id) {
        producentRepository.deleteById(id);
    }
}
