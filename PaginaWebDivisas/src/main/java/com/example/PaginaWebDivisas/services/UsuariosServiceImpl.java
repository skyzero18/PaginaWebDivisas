package com.example.PaginaWebDivisas.services;

import com.example.PaginaWebDivisas.models.Usuarios;
import com.example.PaginaWebDivisas.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa PasswordEncoder
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyecta PasswordEncoder

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
        throw new RuntimeException("No se encontraron usuarios con id " + id);
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        // Encripta la contraseña antes de guardarla
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
                    // Encripta la contraseña si se actualiza
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
            return null; // O lanzar una excepción según tu lógica
        }
        return usuarios;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuariosRepo.deleteById(id);
    }

    @Override
    public Optional<Usuarios> findByUsername(String username) {
        return usuariosRepo.findByNombre(username).stream().findFirst();
    }


}
