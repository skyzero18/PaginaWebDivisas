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
        Optional<Usuarios> usuario = usuariosRepo.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new RuntimeException("no se encontraron usuarios con id " + id);
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
            }
        });

        return usuariosRepo.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuariosRepo.deleteById(id);
    }
}
