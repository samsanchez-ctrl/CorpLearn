package com.corplearn.calificacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corplearn.calificacion.model.Calificacion;
import com.corplearn.calificacion.repository.CalificacionRepository;

@Service
public class CalificationService {
    
    @Autowired
    private CalificacionRepository repository;

    public Calificacion guardar(Calificacion calificacion) {
        calificacion.setId(null); // Forzar AUTO_INCREMENT seguro
        return repository.save(calificacion);
    }

    public List<Calificacion> obtenerTodos() {
        return repository.findAll();
    }

    public List<Calificacion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Calificacion> obtenerPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
