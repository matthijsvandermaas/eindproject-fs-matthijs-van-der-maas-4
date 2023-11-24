package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.AuthenticationRequest;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security.JwtService;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security.MyUserDetailsService;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
//    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.jwtService = jwtService;
    }

    // Deze methode geeft het JWT-token terug
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(up);

            UserDetails ud = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(headers);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
