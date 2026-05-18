package com.corplearn.inscripcion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.corplearn.inscripcion.dto.CursoDto;
import com.corplearn.inscripcion.model.Inscripcion;
import com.corplearn.inscripcion.repository.InscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    public Inscripcion inscribir(Inscripcion inscripcion) {
        // 1. Validar duplicados: ¿Ya esta inscrito?
        if (repository.findByUsuarioIdAndCursoId(inscripcion.getUsuarioId(), inscripcion.getCursoId()).isPresent()) {
            throw new RuntimeException("El usuario ya se encuentra inscrito en este curso.");
        }

        // 2. Conexion inter-servicio nativa: Validar si el curso existe en curso
        try {
            String urlCurso = "http://localhost:8093/cursos/" + inscripcion.getCursoId();
            CursoDto curso = restTemplate.getForObject(urlCurso, CursoDto.class);
            if (curso == null) {
                throw new RuntimeException("El curso especificado no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el servicio de Cursos: " + e.getMessage());
        }

        //  3. Procesar y guardar la inscripcion
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        return repository.save(inscripcion);
    }

    public List<Inscripcion> obtenerTodas() {
        return repository.findAll();
    }

    public List<Inscripcion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public void cancelarInscripcion(Long id) {
        Inscripcion inscripcion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con el ID: " + id));
        repository.delete(inscripcion);
    }
}
