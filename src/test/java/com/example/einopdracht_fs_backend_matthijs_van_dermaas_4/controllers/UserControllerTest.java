// UserControllerTest.java
package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileAndUserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUserWithProfile() throws Exception {
        ProfileAndUserDto profileDto = new ProfileAndUserDto();
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userService.createUserWithProfile(profileDto)).thenReturn(new UserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/createWithProfile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetUserById() throws Exception {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setPassword("password");
        mockUser.setUsername("username");

        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"));
    }
}
