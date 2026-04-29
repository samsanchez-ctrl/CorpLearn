package com.microservicio.mensajeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservicio.mensajeria.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    
}
