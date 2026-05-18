package com.corplearn.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.corplearn.register.dto.RegisterRequest;
import com.corplearn.register.dto.RolDto;
import com.corplearn.register.model.Usuario;
import com.corplearn.register.repository.RegisterRepository;

@Service
public class RegisterService {
    
    @Autowired
    private RegisterRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    public Usuario registrarUsuario(RegisterRequest request) {
        // 1. Validar que el correo no este duplicado
        if(repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        // Logica CorpLearn: Asignar Rol por defecto (ID: 1, se creo como prueba en PostMan)
        Long rolPorDefectoId = 1L;

        // Conexion inter-servicio real (Consumo directo de API por HTTP)
        try {
            String urlPorRoles = "http://localhost:8090/roles/" + rolPorDefectoId;
            RolDto rolDB = restTemplate.getForObject(urlPorRoles, RolDto.class);

            if (rolDB == null) {
                throw new RuntimeException("El rol por defecto no se encuentra configurado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error en comunicacion nativa con el servicio de Roles: " + e.getMessage());
        }

        // Persistir el nuevo usuario si la comunicacion fue exitosa
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRolId(rolPorDefectoId);

        return repository.save(usuario);
    }
}
