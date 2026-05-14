package com.microservicio.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservicio.login.dto.LoginRequest;
import com.microservicio.login.model.UsuarioLogin;
import com.microservicio.login.repository.LoginRepository;

import lombok.Data;

@Service
@Data
public class LoginService {
    @Autowired
    private LoginRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object validar(LoginRequest request) {
        UsuarioLogin u = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            return "{\"mensaje\": \"Ingreso exitoso\", \"rolId\": "+ u.getRolId() + "}";
        }
        throw new RuntimeException("Clave Incorrecta");
    }
}
