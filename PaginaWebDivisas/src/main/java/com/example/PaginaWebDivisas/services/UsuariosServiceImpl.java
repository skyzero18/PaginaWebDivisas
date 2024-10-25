package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepo.findAll();
    }

    @Override
    public Usuarios getUsuarioById(Long id) {
        return usuariosRepo.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontraron usuarios con id " + id)
        );
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        // Encriptar la contraseña antes de guardar
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
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
                    // Encriptar la contraseña si se actualiza
                    existingUsuario.setContraseña(passwordEncoder.encode((String) value));
                    break;
            }
        });

        return usuariosRepo.save(existingUsuario);
    }

    @Override
    public List<Usuarios> findByNombre(String nombre) {
        List<Usuarios> usuarios = usuariosRepo.findByNombre(nombre);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios con nombre " + nombre);
        }
        return usuarios;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuariosRepo.deleteById(id);
    }

    @Override
    public Optional<Usuarios> findByUsername(String username) {
        // Asume que `UsuariosRepo` tiene un método `findByNombre`
        return usuariosRepo.findByNombre(username).stream().findFirst();
    }
}
