package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


    // Methode voor het genereren van een JWT-token
    public class JwtUtil {
        public String generateToken(String subject, String secretKey) {
            return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, secretKey).compact();
        }

        public Jws<Claims> parseToken(String token, String secretKey) {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        }
    }

