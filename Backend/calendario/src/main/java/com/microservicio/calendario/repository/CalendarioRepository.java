package com.microservicio.calendario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.calendario.model.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
    
}
