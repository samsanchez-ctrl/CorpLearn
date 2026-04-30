package com.micriservicio.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micriservicio.usuario.model.Usuario;
import com.micriservicio.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario registrarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> login(String email, String password) {
        return repository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return repository.findAll();
    }
}

