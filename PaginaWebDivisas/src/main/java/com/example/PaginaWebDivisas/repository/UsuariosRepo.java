package com.example.PaginaWebDivisas.repository;

import com.example.PaginaWebDivisas.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuariosRepo extends JpaRepository<Usuarios, Long> {
    // Método para encontrar usuarios por nombre
    List<Usuarios> findByNombre(String nombre);

    // Método para encontrar un usuario por nombre de usuario
    Optional<Usuarios> findByUsername(String username);

    // Método para encontrar un usuario por email
    Optional<Usuarios> findByEmail(String email);
}
