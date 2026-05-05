package com.microservicio.mensajeria.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.mensajeria.dto.UsuarioDto;
import com.microservicio.mensajeria.model.Mensaje;
import com.microservicio.mensajeria.repository.MensajeRepository;

@Service
public class MensajeService {
    
    @Autowired
    private MensajeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Mensaje> listar(){
        return repository.findAll();
    }

    public Mensaje enviar(Mensaje mensaje){
        try {
        // 1. Validar Emisor
        UsuarioDto emisor = restTemplate.getForObject(
            "http://localhost:8090/usuario/" + mensaje.getEmisorId(), UsuarioDto.class);

        // 2. Validar Receptor
        UsuarioDto receptor = restTemplate.getForObject(
            "http://localhost:8090/usuario/" + mensaje.getReceptorId(), UsuarioDto.class);

        if (emisor != null && receptor != null) {
            return repository.save(mensaje);
        } else {
            throw new RuntimeException("Error: El emisor o el receptor no son usuarios válidos.");
        }
    } catch (Exception e) {
        throw new RuntimeException("Error de comunicación: El microservicio de Usuario no responde.");
    }
    }
}
