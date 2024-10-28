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
@CrossOrigin(origins = "https://cotizacionesdefi.com", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        var usuarioOptional = usuariosService.findByUsername(username);

        if (usuarioOptional.isPresent() && passwordEncoder.matches(password, usuarioOptional.get().getContraseña())) {
            httpSession.setAttribute("user", username);
            System.out.println("Usuario almacenado en sesión: " + username);
            System.out.println("ID de sesión en login: " + httpSession.getId());
            return ResponseEntity.ok().body(Map.of("redirectUrl", "/admin"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            return ResponseEntity.ok(Map.of("redirectUrl", "/login"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cerrar la sesión");
        }
    }

    @PostMapping("/inicio")
    public ResponseEntity<?> inicio() {
        return ResponseEntity.ok().body(Map.of("redirectUrl", "/"));
    }

    @PostMapping("/loginpan")
    public ResponseEntity<?> irlogi() {
        return ResponseEntity.ok().body(Map.of("redirectUrl", "/login"));
    }

    @GetMapping("/checkSession")
    public ResponseEntity<?> checkSession(HttpSession session) {
        String user = (String) session.getAttribute("user");
        System.out.println("ID de sesión en checkSession: " + session.getId());
        System.out.println("Usuario recuperado de la sesión: " + user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("redirectUrl", "/login"));
        } else {
            return ResponseEntity.ok().body("Usuario autenticado: " + user);
        }
    }


    @GetMapping("/checkSession2")
    public ResponseEntity<?> checkSession2(HttpSession session) {
        String user = (String) session.getAttribute("user");
        System.out.println("ID de sesión en checkSession: " + session.getId());
        System.out.println("Usuario recuperado de la sesión: " + user);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("redirectUrl", "/admin"));
        } else {
            return ResponseEntity.ok().body("Usuario autenticado: " + user);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifySession() {
        String user = (String) httpSession.getAttribute("user");
        System.out.println("Usuario de la sesión: " + user);
        System.out.println("ID de sesión en verify: " + httpSession.getId());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay usuario autenticado");
        }

        return ResponseEntity.ok().body("Usuario autenticado: " + user);
    }
}

