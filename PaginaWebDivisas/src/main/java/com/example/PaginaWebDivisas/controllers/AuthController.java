package com.example.PaginaWebDivisas.controllers;

import com.example.PaginaWebDivisas.DTO.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController { // Cambié el nombre para seguir la convención PascalCase

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Autenticar el usuario con un servicio real o acceso a base de datos

        boolean isAuthenticated = authenticate(loginRequest.getNombre(), loginRequest.getContraseña());

        if (isAuthenticated) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

    }

    private boolean authenticate(String username, String password) {
        if (username == null) {
            System.out.println("El nombre de usuario es nulo");
            return false;
        }
        return username.equals("admin") && password.equals("admin123");
    }
}
