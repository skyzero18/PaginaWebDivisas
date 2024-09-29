package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Divisas;

import java.util.List;

public interface DivisasService {
    List<Divisas> getAllDivisas();
    Divisas getDivisaById(Long id);
    Divisas saveDivisa(Divisas divisas);
    Divisas updateDivisa(Long id, Divisas divisas);
    void deleteDivisa(Long id);
}
