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
        throw new RuntimeException("no se encontraron divisas con id " + id);

    }

    @Override
    public Divisas saveDivisa(Divisas divisas) {
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
                    existingDivisa.setCompra((String) value);
                    break;
                case "venta":
                    existingDivisa.setVenta((String) value);
                    break;
            }
        });

        return divisasRepo.save(existingDivisa);
    }


    @Override
    public void deleteDivisa(Long id) {
        divisasRepo.deleteById(id);
    }
}
