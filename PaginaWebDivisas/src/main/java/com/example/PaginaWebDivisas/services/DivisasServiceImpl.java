package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Divisas;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.DivisasRepo;
import com.example.PaginaWebDivisas.repository.LogsRepo;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DivisasServiceImpl implements DivisasService {

    @Autowired
    private DivisasRepo divisasRepo;

    @Autowired
    private LogsRepo logsRepo;

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Override
    public List<Divisas> getAllDivisas() {
        return divisasRepo.findAll();
    }

    @Override
    public Divisas getDivisaById(Long id) {
        Optional<Divisas> divisas = divisasRepo.findById(id);
        if (divisas.isPresent()) {
            return divisas.get();
        }
        throw new RuntimeException("No se encontraron divisas con id " + id);
    }

    @Override
    public Divisas saveDivisa(Divisas divisas) {
        return null;
    }

    @Override
    public Divisas patchDivisa(Long id, Map<String, Object> updates) {
        return null;
    }

    @Override
    public void deleteDivisa(Long id) {

    }

    @Override
    public Divisas saveDivisa(Divisas divisas, Long usuarioId) {
        Divisas savedDivisa = divisasRepo.save(divisas);

        // Registrar log tras la creación de la divisa
        registrarLog("Divisa creada", usuarioId);
        return savedDivisa;
    }

    @Override
    public Divisas patchDivisa(Long id, Map<String, Object> updates, Long usuarioId) {
        Divisas existingDivisa = getDivisaById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    existingDivisa.setNombre((String) value);
                    break;
                case "compra":
                    existingDivisa.setCompra(Float.parseFloat(value.toString())); // Cambiado a float
                    break;
                case "venta":
                    existingDivisa.setVenta(Float.parseFloat(value.toString())); // Cambiado a float
                    break;
                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + key);
            }
        });

        Divisas updatedDivisa = divisasRepo.save(existingDivisa);
        registrarLog("Divisa modificada", usuarioId);
        return updatedDivisa;
    }


    @Override
    public void deleteDivisa(Long id, Long usuarioId) {
        divisasRepo.deleteById(id);

        // Registrar log tras la eliminación de la divisa
        registrarLog("Divisa eliminada", usuarioId);
    }

    // Método auxiliar para registrar logs
    private void registrarLog(String accion, Long usuarioId) {
        Optional<Usuarios> usuarioOpt = usuariosRepo.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();
            Logs log = new Logs();
            log.setNombre(accion);
            log.setUsuarios(usuario);
            log.setFechaCreacion(LocalDateTime.now()); // JPA puede gestionar automáticamente este campo
            logsRepo.save(log);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado para registrar el log");
        }
    }
}
