package com.example.PaginaWebDivisas.controllers;
import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping
    public List<Usuarios> getAllClientes() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuarios getUsuariosById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping
    public Usuarios createUsuarios(@RequestBody Usuarios usuarios) {
        return usuarioService.saveUsuario(usuarios);
    }

    @PatchMapping("/{id}")
    public Usuarios patchUsuarios (@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return usuarioService.patchUsuario(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuarios(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}