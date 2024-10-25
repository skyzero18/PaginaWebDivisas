package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Logs;
import java.util.List;
import java.util.Map;

public interface LogsService {
    List<Logs> getAllLogs();
    Logs getLogById(Long id);
    Logs saveLog(Logs logs);
    Logs patchLog(Long id, Map<String, Object> updates);
    void deleteLog(Long id);

    // Método para buscar logs por el ID de un usuario
    List<Logs> findLogsByUsuario(Long usuarioId);
}
