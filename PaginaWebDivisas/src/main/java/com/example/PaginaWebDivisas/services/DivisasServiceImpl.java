package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Divisas;

import com.example.PaginaWebDivisas.repository.DivisasRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Divisas updateDivisa(Long id, Divisas divisa) {
        Divisas existingDivisa = getDivisaById(id);

        existingDivisa.setNombre(divisa.getNombre());
        existingDivisa.setCompra(divisa.getCompra());
        existingDivisa.setVenta(divisa.getVenta());

        return divisasRepo.save(existingDivisa);
    }


    @Override
    public void deleteDivisa(Long id) {
        divisasRepo.deleteById(id);
    }
}
