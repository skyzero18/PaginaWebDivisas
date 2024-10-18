package com.example.PaginaWebDivisas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hola, bienvenido a PaginaWebDivisas!");
        return "inicio"; // El nombre del archivo HTML en src/main/resources/templates/
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "inicioAdmin";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}