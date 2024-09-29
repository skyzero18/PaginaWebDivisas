package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Usuarios;

import java.util.List;

public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Usuarios getUsuarioById(Long id);
    Usuarios saveUsuario(Usuarios usuario);
    Usuarios updateUsuario(Long id, Usuarios usuario);
    void deleteUsuario(Long id);
}
