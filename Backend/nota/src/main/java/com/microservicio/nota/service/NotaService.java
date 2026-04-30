package com.microservicio.nota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.nota.dto.ModuloDto;
import com.microservicio.nota.dto.UsuarioDto;
import com.microservicio.nota.model.Nota;
import com.microservicio.nota.repository.NotaRepository;

@Service
public class NotaService {
    
    @Autowired
    private NotaRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    
    public Nota subirNota(Nota n) { 
        try {
            // 1. Validar el Alumno
            UsuarioDto usuario = restTemplate.getForObject(
                "http://localhost:8090/usuarios/" + n.getAlumnoId(), UsuarioDto.class);

            // 2. Validar el Módulo
            ModuloDto modulo = restTemplate.getForObject(
                "http://localhost:8085/modulos/" + n.getModuloId(), ModuloDto.class);

            if (usuario != null && modulo != null) {
                return repository.save(n);
            } else {
                throw new RuntimeException("Error: El Alumno o el Módulo no existen.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de conexión: El microservicio de Usuario o Módulo no responde.");
        }
    }   
}
