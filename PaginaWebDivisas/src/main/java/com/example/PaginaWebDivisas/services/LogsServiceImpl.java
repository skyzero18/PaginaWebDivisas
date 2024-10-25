package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Divisas;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.DivisasRepo;
import com.example.PaginaWebDivisas.repository.LogsRepo;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private DivisasRepo divisasRepo;

    @Override
    public List<Logs> getAllLogs() {
        return logsRepo.findAll();
    }

    @Override
    public Logs getLogById(Long id) {
        return logsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontraron logs con id " + id));
    }
    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) ? authentication.getName() : "Desconocido";
    }


    @Override
    public Logs saveLog(Logs logs) {
        // Obtener el usuario autenticado
        String username = getAuthenticatedUsername();

        // Buscar al usuario autenticado en la base de datos
        Usuarios usuarioAutenticado = findUsuarioByUsername(username);

        // Asignar el usuario autenticado al log
        logs.setUsuarios(usuarioAutenticado);

        // Guardar el log
        return logsRepo.save(logs);
    }

    private Divisas extractDivisaFromMap(Map<?, ?> divisaMap) {
        if (divisaMap.containsKey("id") && divisaMap.get("id") instanceof Number) {
            Long divisaId = ((Number) divisaMap.get("id")).longValue();
            return findDivisaById(divisaId);
        } else {
            throw new IllegalArgumentException("Campo 'id' no válido en 'divisas'");
        }
    }
    public class LogNotFoundException extends RuntimeException {
        public LogNotFoundException(Long id) {
            super("No se encontraron logs con id " + id);
        }
    }
    @Override
    public Logs patchLog(Long id, Map<String, Object> updates) {
        Logs existingLog = getLogById(id);

        // Obtener el usuario autenticado
        String username = getAuthenticatedUsername();
        Usuarios usuarioAutenticado = findUsuarioByUsername(username);

        // Procesar las actualizaciones
        updates.forEach((key, value) -> {
            switch (key) {
                case "divisas":
                    if (value instanceof Map<?, ?> divisaMap) {
                        Divisas divisa = extractDivisaFromMap(divisaMap);
                        existingLog.setDivisas(divisa);
                    } else {
                        throw new IllegalArgumentException("Valor no válido para 'divisas'");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + key);
            }
        });

        // Registrar el usuario que realizó la modificación en el log
        existingLog.setUsuarios(usuarioAutenticado);

        return logsRepo.save(existingLog);
    }



    private Usuarios findUsuarioById(Long usuarioId) {
        return usuariosRepo.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con id " + usuarioId + " no encontrado."));
    }

    private Divisas findDivisaById(Long divisaId) {
        return divisasRepo.findById(divisaId)
                .orElseThrow(() -> new IllegalArgumentException("Divisa con id " + divisaId + " no encontrada."));
    }

    private Usuarios findUsuarioByUsername(String username) {
        return usuariosRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario " + username + " no encontrado."));
    }

    @Override
    public void deleteLog(Long id) {
        logsRepo.deleteById(id);
    }
}
