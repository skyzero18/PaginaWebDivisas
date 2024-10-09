package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Divisas;
import com.example.PaginaWebDivisas.repository.DivisasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DivisasServiceImpl implements DivisasService {

    @Autowired
    private DivisasRepo divisasRepo;





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

        return divisasRepo.save(divisas);
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

        return divisasRepo.save(existingDivisa);
    }


    @Override
    public void deleteDivisa(Long id, Long usuarioId) {
        divisasRepo.deleteById(id);
    }


}
