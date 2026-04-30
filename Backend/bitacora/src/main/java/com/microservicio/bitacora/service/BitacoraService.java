package com.microservicio.bitacora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.bitacora.dto.CalendarioDto;
import com.microservicio.bitacora.model.Bitacora;
import com.microservicio.bitacora.repository.BitacoraRepository;

@Service
public class BitacoraService {
    
    @Autowired
    private BitacoraRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Bitacora> listarTodas(){
        return repository.findAll();
    }

    public Bitacora guardar(Bitacora bitacora) {
        try {
            // Validar que la sesión exista en el microservicio Calendario
            CalendarioDto sesion = restTemplate.getForObject(
                "http://localhost:8083/calendario/" + bitacora.getHorarioId(), CalendarioDto.class);

            if (sesion != null) {
                return repository.save(bitacora);
            } else {
                throw new RuntimeException("Error: La sesión de calendario no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de comunicación: El microservicio de Calendario no responde.");
        }
    }

    public Bitacora obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
