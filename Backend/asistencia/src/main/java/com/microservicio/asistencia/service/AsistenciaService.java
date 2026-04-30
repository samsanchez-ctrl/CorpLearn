package com.microservicio.asistencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.asistencia.dto.CalendarioDto;
import com.microservicio.asistencia.dto.UsuarioDto;
import com.microservicio.asistencia.model.Asistencia;
import com.microservicio.asistencia.repository.AsistenciaRepository;

@Service
public class AsistenciaService {
    
    @Autowired
    private AsistenciaRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    
    public Asistencia registrar(Asistencia a) { 
        try {
            // 1. Validar el Horario en Calendario
            CalendarioDto sesion = restTemplate.getForObject(
                "http://localhost:8083/calendario/" + a.getHorarioId(), CalendarioDto.class);

            // 2. Validar el Usuario/Alumno
            UsuarioDto usuario = restTemplate.getForObject(
                "http://localhost:8090/usuarios/" + a.getAlumnoId(), UsuarioDto.class);

            if (sesion != null && usuario != null) {
                return repository.save(a);
            } else {
                throw new RuntimeException("Error: El horario o el alumno no existen.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de conexión: El microservicio de Calendario o Usuario no responde.");
        } 
    }
    public List<Asistencia> porAlumno(Long alumnoId) { 
        return repository.findAll(); 
    } /* Logica de filtrado */
}
