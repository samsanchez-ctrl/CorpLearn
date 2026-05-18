package com.corplearn.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.curso.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Long> {
    
}
