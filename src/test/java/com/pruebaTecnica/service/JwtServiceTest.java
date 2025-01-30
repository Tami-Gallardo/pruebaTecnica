package com.pruebaTecnica.service;

import com.pruebaTecnica.service.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    private final String username = "testUser";

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken(username);
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(username);
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void testTokenValidation() {
        String token = jwtService.generateToken(username);
        boolean isValid = jwtService.isTokenValid(token, username);
        assertTrue(isValid);
    }
}
