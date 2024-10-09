package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Divisas;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.DivisasRepo;
import com.example.PaginaWebDivisas.repository.LogsRepo;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import jakarta.servlet.http.HttpSession;
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
    private HttpSession httpSession;
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
    public Divisas saveDivisa(Divisas divisas, HttpSession httpSession) {
        divisas.setStatus(true);
        Divisas nuevaDivisa = divisasRepo.save(divisas);
        Usuarios usuarioEjemplo = usuariosRepo.findById(1L).orElse(null);
        Logs nuevoLog = new Logs();
        nuevoLog.setFechaCreacion(LocalDateTime.now());
        nuevoLog.setDivisas(nuevaDivisa);
        nuevoLog.setUsuarios(usuarioEjemplo);

        String user = (String) httpSession.getAttribute("user");
        System.out.println("usuario de la sesion " + user);

        logsRepo.save(nuevoLog);
        return divisasRepo.save(divisas);
    }

    @Override
    public Divisas patchDivisa(Long id, Map<String, Object> updates) {
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
                case "status":
                    existingDivisa.setStatus(Boolean.parseBoolean(value.toString())); // Cambiado a float
                    break;
                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + key);
            }
        });
        Usuarios usuarioEjemplo = usuariosRepo.findById(1L).orElse(null);
        Logs nuevoLog = new Logs();
        nuevoLog.setFechaCreacion(LocalDateTime.now());
        nuevoLog.setDivisas(existingDivisa);
        nuevoLog.setUsuarios(usuarioEjemplo);
        logsRepo.save(nuevoLog);
        return divisasRepo.save(existingDivisa);
    }


    @Override
    public void deleteDivisa(Long id, Long usuarioId) {
        divisasRepo.deleteById(id);
    }


}
