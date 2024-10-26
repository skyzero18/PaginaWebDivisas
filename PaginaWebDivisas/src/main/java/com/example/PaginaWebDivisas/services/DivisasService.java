package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Divisas;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

public interface DivisasService {
    List<Divisas> getAllDivisas();
    Divisas getDivisaById(Long id);
    Divisas saveDivisa(Divisas divisas);
    Divisas patchDivisa(Long id, Map<String, Object> updates, Divisas divisas);
    void deleteDivisa(Long id, Long usuarioId);
}
