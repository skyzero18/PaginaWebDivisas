package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Usuarios;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Usuarios getUsuarioById(Long id);
    Usuarios saveUsuario(Usuarios usuario);
    Usuarios patchUsuario(Long id, Map<String, Object> updates);
    List<Usuarios> findByNombre(String nombre);
    void deleteUsuario(Long id);

    // Agrega el nuevo método
    Optional<Usuarios> findByUsername(String username);
}
