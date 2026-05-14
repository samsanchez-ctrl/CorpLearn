package com.microservicio.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservicio.register.dto.RegisterRequest;
import com.microservicio.register.model.UsuarioRegister;
import com.microservicio.register.repository.RegisterRepository;

import lombok.Data;

@Service
@Data
public class RegisterService {
    @Autowired
    private RegisterRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioRegister crear(RegisterRequest request) {
        UsuarioRegister u = new UsuarioRegister();
        u.setNombre(request.getNombre());
        u.setEmail(request.getEmail());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setRolId(2L);
        return repository.save(u);
    }
}
