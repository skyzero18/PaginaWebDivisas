package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    @Autowired
    private UsuariosRepo usuariosRepo;

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepo.findAll();
    }

    @Override
    public Usuarios getUsuarioById(Long id) {
        return usuariosRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con id " + id));
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        return usuariosRepo.save(usuario);
    }

    @Override
    public Usuarios patchUsuario(Long id, Map<String, Object> updates) {
        Usuarios existingUsuario = getUsuarioById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    existingUsuario.setNombre((String) value);
                    break;
                case "contraseña":
                    existingUsuario.setContraseña((String) value);
                    break;
                // Añade más casos según tus campos
                default:
                    throw new IllegalArgumentException("Campo no reconocido: " + key);
            }
        });

        return usuariosRepo.save(existingUsuario);
    }

    @Override
    public List<Usuarios> findByNombre(String nombre) {
        return usuariosRepo.findByNombre(nombre); // Asegúrate de que este método esté definido en tu repositorio
    }

    @Override
    public void deleteUsuario(Long id) {
        usuariosRepo.deleteById(id);
    }

    @Override
    public Optional<Usuarios> findByUsername(String username) {
        return usuariosRepo.findByUsername(username); // Asegúrate de que este método esté definido en tu repositorio
    }

    @Override
    public Optional<Usuarios> findByEmail(String email) {
        return usuariosRepo.findByEmail(email); // Asegúrate de que este método esté definido en tu repositorio
    }
}
