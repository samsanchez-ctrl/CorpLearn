package com.micriservicio.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micriservicio.usuario.dto.RolDto;
import com.micriservicio.usuario.model.Usuario;
import com.micriservicio.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Usuario registrarUsuario(Usuario usuario) {
        try {
        RolDto rol = restTemplate.getForObject("http://localhost:8089/api/rol/" + usuario.getId(), RolDto.class);

        if (rol != null && rol.getId() != null) {
            // Si el rol existe, procedemos a guardar en la DB de Usuarios
            return repository.save(usuario);
        } else {
            throw new RuntimeException("Error: El Rol con ID " + usuario.getId() + " no existe.");
        }
    } catch (Exception e) {
        // En caso de que el microservicio de ROL esté apagado
        throw new RuntimeException("Error de conexión: El microservicio de Roles no responde.");
    }
        
    }

    public Optional<Usuario> login(String email, String password) {
        return repository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return repository.findAll();
    }
}

