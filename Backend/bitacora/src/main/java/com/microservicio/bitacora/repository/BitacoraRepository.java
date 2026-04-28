package com.microservicio.bitacora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.bitacora.model.Bitacora;

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    
}
