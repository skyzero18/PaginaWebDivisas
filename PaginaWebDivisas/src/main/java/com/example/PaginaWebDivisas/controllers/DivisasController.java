package com.example.PaginaWebDivisas.controllers;
import com.example.PaginaWebDivisas.models.Divisas;
import com.example.PaginaWebDivisas.services.DivisasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/divisas")
@CrossOrigin(origins = "https://www.cotizacionesdefi.com")
public class DivisasController {

    @Autowired
    private DivisasService divisasService;

    @GetMapping
    public List<Divisas> getAllDivisas() {
        return divisasService.getAllDivisas();
    }

    @GetMapping("/{id}")
    public Divisas getDivisaById(@PathVariable Long id) {
        return divisasService.getDivisaById(id);
    }

    @PostMapping
    public Divisas createDivisa(@RequestBody Divisas divisas) {
        return divisasService.saveDivisa(divisas);
    }

    @PatchMapping("/{id}")
    public Divisas patchDivisas(@PathVariable Long id, @RequestBody Map<String, Object> updates, Divisas divisas) {
        return divisasService.patchDivisa(id, updates, divisas);
    }

    @DeleteMapping("/{id}")
    public void deleteDivisa(@PathVariable Long id, @PathVariable Long usuarioId) {
        divisasService.deleteDivisa(id, usuarioId);
    }
}
