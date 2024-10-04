package com.example.PaginaWebDivisas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String nombre;    // Nombre de usuario
    private String contraseña; // Contraseña
}