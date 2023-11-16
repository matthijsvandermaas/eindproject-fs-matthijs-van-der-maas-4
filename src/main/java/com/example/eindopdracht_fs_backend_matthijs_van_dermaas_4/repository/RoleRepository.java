package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.repository;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRolesByUsersUsername(String username);

    Role findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.roles = :roles WHERE u.id = :userId")
    void addRoleToUser(@Param("userId") Long userId, @Param("roles") List<Role> roles);
}





