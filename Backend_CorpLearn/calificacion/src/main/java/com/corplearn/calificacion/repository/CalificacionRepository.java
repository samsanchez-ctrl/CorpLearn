package com.corplearn.calificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.calificacion.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByUsuarioId(Long usuarioId);
    List<Calificacion> findByCursoId(Long cursoId);
}
