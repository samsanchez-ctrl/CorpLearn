package com.microservicio.mensajeria.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.mensajeria.model.Mensaje;
import com.microservicio.mensajeria.repository.MensajeRepository;

@Service
public class MensajeService {
    
    @Autowired
    private MensajeRepository repository;

    public List<Mensaje> listar(){
        return repository.findAll();
    }

    public Mensaje enviar(Mensaje mensaje){
        mensaje.setFecha(LocalDateTime.now());
        return repository.save(mensaje);
    }
}
