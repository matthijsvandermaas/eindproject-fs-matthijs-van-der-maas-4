package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.AuthDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.JwtService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.MyUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, MyUserDetails ud) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@Valid @RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

        try {
            Authentication auth = authManager.authenticate(up);

            MyUserDetails ud = (MyUserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body("Token generated");
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}