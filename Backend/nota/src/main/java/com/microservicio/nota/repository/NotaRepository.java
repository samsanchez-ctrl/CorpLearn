package com.microservicio.nota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.nota.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    
}
