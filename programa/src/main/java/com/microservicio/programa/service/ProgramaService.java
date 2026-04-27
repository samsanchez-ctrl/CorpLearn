package com.microservicio.programa.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.programa.model.Programa;
import com.microservicio.programa.repository.ProgramaRepository;

@Service
public class ProgramaService {

    private final ProgramaRepository programaRepository;

    public ProgramaService(ProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }

    public Programa guardarPrograma(Programa programa) {
        return programaRepository.save(programa);
    }

    public List<Programa> listarTodo() {
        return programaRepository.findAll();
    }
}