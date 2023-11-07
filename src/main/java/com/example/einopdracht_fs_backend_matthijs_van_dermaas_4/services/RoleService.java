package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.RoleDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {
    private final RoleRepository repos;

    public RoleService(RoleRepository repos) {
        this.repos = repos;
    }

    public List<RoleDto> getRoles() {


        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role r : repos.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.setRoleName(r.getRoleName());

            roleDtos.add(rdto);
        }
        return roleDtos;
    }
}