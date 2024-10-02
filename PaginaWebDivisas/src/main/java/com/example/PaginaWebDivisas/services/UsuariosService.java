package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Usuarios;

import java.util.List;
import java.util.Map;

public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Usuarios getUsuarioById(Long id);
    Usuarios saveUsuario(Usuarios usuario);
    Usuarios patchUsuario(Long id, Map<String, Object> updates);
    void deleteUsuario(Long id);
}
