package com.corplearn.inscripcion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.inscripcion.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository <Inscripcion, Long> {
    Optional<Inscripcion> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    List<Inscripcion> findByUsuarioId(Long usuarioId); // Para listar las inscripciones de un alumno
}
