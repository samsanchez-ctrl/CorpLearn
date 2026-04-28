package com.microservicio.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.asistencia.model.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    
}
