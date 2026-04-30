package com.microservicio.calendario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.calendario.dto.ModuloDto;
import com.microservicio.calendario.dto.UsuarioDto;
import com.microservicio.calendario.model.Calendario;
import com.microservicio.calendario.repository.CalendarioRepository;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Calendario> listarTodos() { 
        return repository.findAll(); 
    }
    public Calendario guardar(Calendario c) { 
        try {
            // Validar el Módulo
            ModuloDto modulo = restTemplate.getForObject(
                "http://localhost:8085/modulos/" + c.getModuloId(), ModuloDto.class);

            // 2. Validar el Usuario/Relator
            UsuarioDto usuario = restTemplate.getForObject(
                "http://localhost:8090/usuarios/" + c.getRelatorId(), UsuarioDto.class);

            if (modulo != null && usuario != null) {
                return repository.save(c);
            } else {
                throw new RuntimeException("Error: El Módulo o el Usuario no existen.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de comunicación: Uno de los microservicios no responde.");
        }
    } 
    
    public void eliminar(Long id) { 
        repository.deleteById(id);
    }
}
