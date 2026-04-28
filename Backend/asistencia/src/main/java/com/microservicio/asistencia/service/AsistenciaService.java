package com.microservicio.asistencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.asistencia.model.Asistencia;
import com.microservicio.asistencia.repository.AsistenciaRepository;

@Service
public class AsistenciaService {
    
    @Autowired
    private AsistenciaRepository repo;
    
    public Asistencia registrar(Asistencia a) { return repo.save(a); }
    public List<Asistencia> porAlumno(Long alumnoId) { return repo.findAll(); } /* Logica de filtrado */
}
