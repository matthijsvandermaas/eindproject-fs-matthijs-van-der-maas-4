package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProfileAndUserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.UserDto;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security.JwtService;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

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

    @MockBean
    public JwtService jwtService;

    @MockBean
    public UserDetailsService userDetailsService;

    @MockBean
    public PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUserWithProfile() throws Exception {
        ProfileAndUserDto profileDto = new ProfileAndUserDto();
        profileDto.setPassword("testPassword");

        when(passwordEncoder.encode(eq("testPassword"))).thenReturn("encodedPassword");
        when(userService.createUserWithUserDto(profileDto)).thenReturn(new UserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/createWithProfile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());

        verify(passwordEncoder, times(1)).encode(eq("testPassword"));
        verify(userService, times(1)).createUserWithUserDto(profileDto);
    }

    @Test
    void testGetUserById() throws Exception {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setPassword("testPassword");
        mockUser.setUsername("username");

        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testPassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
