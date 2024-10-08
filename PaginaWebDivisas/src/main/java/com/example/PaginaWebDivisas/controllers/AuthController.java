package com.example.PaginaWebDivisas.controllers;

import com.example.PaginaWebDivisas.services.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Importar PasswordEncoder
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if ("admin".equals(username) && "password".equals(password)) {
            session.setAttribute("user", username);
            System.out.println("Usuario almacenado en sesión: " + username);
            System.out.println("ID de sesión en login: " + session.getId());
            return ResponseEntity.ok().body(Map.of("redirectUrl", "/inicioAdmin.html"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");

    }




    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Invalidar la sesión del usuario
        System.out.println("Sesión cerrada para el usuario: ");
        return ResponseEntity.ok().body(Map.of("redirectUrl", "/login.html"));
    }

    @PostMapping("/inicio")
    public ResponseEntity<?> inicio() {
        return ResponseEntity.ok().body(Map.of("redirectUrl", "/inicio.html"));
    }

    @GetMapping("/checkSession")
    public ResponseEntity<?> checkSession(HttpSession session) {
        String user = (String) session.getAttribute("user");
        System.out.println("ID de sesión en checkSession: " + session.getId());
        System.out.println("Usuario recuperado de la sesión: " + user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("redirectUrl", "/login.html"));
        } else {
            return ResponseEntity.ok().body("Usuario autenticado: " + user);
        }
    }

}
