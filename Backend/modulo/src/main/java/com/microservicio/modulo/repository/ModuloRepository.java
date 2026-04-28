package com.microservicio.modulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.modulo.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long>{
}