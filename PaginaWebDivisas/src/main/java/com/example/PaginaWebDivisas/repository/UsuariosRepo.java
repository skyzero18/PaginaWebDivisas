package com.example.PaginaWebDivisas.repository;
import com.example.PaginaWebDivisas.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepo extends JpaRepository<Usuarios, Long> {
    List<Usuarios> findByNombre(String nombre);
}
