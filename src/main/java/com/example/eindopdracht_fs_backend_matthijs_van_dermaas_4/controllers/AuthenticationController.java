package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.controllers;


import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.AuthenticationRequest;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.AuthenticationResponse;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security.MyUserDetailsService;
import com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;

    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

    // Deze methode geeft het JWT-token terug
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        String role = authenticationRequest.getRole();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails myUserDetails = (UserDetails) myUserDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(myUserDetails);
        System.out.println("Generated Token: " + jwt);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
