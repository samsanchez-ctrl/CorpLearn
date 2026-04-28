package com.microservicio.nota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.nota.model.Nota;
import com.microservicio.nota.repository.NotaRepository;

@Service
public class NotaService {
    
    @Autowired
    private NotaRepository repo;
    
    public Nota subirNota(Nota n) { return repo.save(n); }
}
