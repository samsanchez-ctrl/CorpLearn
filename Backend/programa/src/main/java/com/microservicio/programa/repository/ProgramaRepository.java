package com.microservicio.programa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.programa.model.Programa;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
