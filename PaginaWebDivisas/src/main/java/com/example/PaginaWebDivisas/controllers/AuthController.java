package com.example.PaginaWebDivisas.controllers;

import com.example.PaginaWebDivisas.DTO.LoginRequest;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Importar PasswordEncoder
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        List<Usuarios> usuarios = usuariosService.findByNombre(loginRequest.getNombre());
        Map<String, String> response = new HashMap<>();

        // Verifica si hay usuarios con ese nombre
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuarios usuario : usuarios) {
                if (passwordEncoder.matches(loginRequest.getContraseña(), usuario.getContraseña())) {
                    response.put("message", "Inicio de sesión exitoso");
                    return ResponseEntity.ok(response);
                }
            }
        }

        response.put("message", "Usuario o contraseña incorrectos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


}
