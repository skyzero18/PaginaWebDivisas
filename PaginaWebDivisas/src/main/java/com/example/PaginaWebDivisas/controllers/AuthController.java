package com.example.PaginaWebDivisas.controllers;

import com.example.PaginaWebDivisas.services.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    String username;
    String password;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        username = credentials.get("username");
        password = credentials.get("password");
        var usuarioOptional = usuariosService.findByUsername(username);

        if (usuarioOptional.isPresent() && passwordEncoder.matches(password, usuarioOptional.get().getContraseña())) {
            session.setAttribute("user", username);
            System.out.println("Usuario almacenado en sesión: " + username);
            System.out.println("ID de sesión en login: " + session.getId());
            return ResponseEntity.ok().body(Map.of("redirectUrl", "/inicioAdmin.html"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            return ResponseEntity.ok(Map.of("redirectUrl", "/login.html"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cerrar la sesión");
        }
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

    @GetMapping("/verify")
    public ResponseEntity<?> verifySession(HttpSession session) {
        String user = (String) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(Map.of("autenticado", true, "redirectUrl", "/inicioAdmin.html"));
        } else {
            return ResponseEntity.ok(Map.of("autenticado", false, "redirectUrl", "/login.html"));
        }
    }
}
