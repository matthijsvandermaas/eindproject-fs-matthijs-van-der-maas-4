package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducentService {

    private final ProducentRepository producentRepository;

    @Autowired
    public ProducentService(ProducentRepository producentRepository) {
        this.producentRepository = producentRepository;
    }

    public List<Producent> getAllProducers() {
        return producentRepository.findAll();
    }

    public Producent getProducerById(Long id) {
        return producentRepository.findById(id).orElse(null);
    }

    public Producent createProducer(Producent producent) {
        return producentRepository.save(producent);
    }

    public Producent updateProducer(Long id, Producent producent) {
        if (producentRepository.existsById(id)) {
            producent.setId(id);
            return producentRepository.save(producent);
        }
        return null;
    }

    public boolean deleteProducer(Long id) {
        if (producentRepository.existsById(id)) {
            producentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
