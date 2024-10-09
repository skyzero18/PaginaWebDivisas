package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.LogsRepo;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsRepo logsRepo;
    @Autowired
    private UsuariosRepo usuariosRepo;

    @Override
    public List<Logs> getAllLogs() {return logsRepo.findAll();}

    @Override
    public Logs getLogById(Long id) {
        Optional<Logs> logs = logsRepo.findById(id);
        if (logs.isPresent()) {
            return logs.get();
        }
        throw new RuntimeException("no se encontraron logs con id " + id);

    }

    @Override
    public Logs saveLog(Logs logs) {
        return logsRepo.save(logs);
    }

    @Override
    public Logs patchLog(Long id, Map<String, Object> updates) {
        Logs existingLog = getLogById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "usuarios":
                    if (value instanceof Map) {
                        Map<String, Object> usuarioMap = (Map<String, Object>) value;
                        if (usuarioMap.containsKey("id") && usuarioMap.get("id") instanceof Number) {
                            Long usuarioId = ((Number) usuarioMap.get("id")).longValue();

                            // Método para buscar un usuario por id (ejemplo: findUsuarioById)
                            Usuarios usuario = findUsuarioById(usuarioId);

                            if (usuario != null) {
                                existingLog.setUsuarios(usuario);
                            } else {
                                throw new IllegalArgumentException("Usuario no encontrado con id: " + usuarioId);
                            }
                        } else {
                            throw new IllegalArgumentException("Campo 'id' no válido en 'usuarios': " + usuarioMap);
                        }
                    } else {
                        throw new IllegalArgumentException("Valor no válido para 'usuarios': " + value);
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + key);
            }
        });

        return logsRepo.save(existingLog);
    }



    private Usuarios findUsuarioById(Long usuarioId) {
        return usuariosRepo.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con id " + usuarioId + " no encontrado."));
    }



    @Override
    public void deleteLog(Long id) {
        logsRepo.deleteById(id);
    }
}
