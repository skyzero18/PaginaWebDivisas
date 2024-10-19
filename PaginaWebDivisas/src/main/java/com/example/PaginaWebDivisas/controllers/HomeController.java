package com.example.PaginaWebDivisas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "https://cotizacionesdefi.com")
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hola, bienvenido a PaginaWebDivisas!");
        return "inicio.html"; // El nombre del archivo HTML en src/main/resources/templates/
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "inicioAdmin.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login.html";
    }
}