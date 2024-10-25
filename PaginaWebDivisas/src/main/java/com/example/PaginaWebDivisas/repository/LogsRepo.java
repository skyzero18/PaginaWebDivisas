package com.example.PaginaWebDivisas.repository;

import com.example.PaginaWebDivisas.models.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogsRepo extends JpaRepository<Logs, Long> {
    // Método para encontrar logs por el ID del usuario
    List<Logs> findByUsuariosId(Long usuarioId);
}
