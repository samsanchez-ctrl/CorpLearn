package com.microservicio.calendario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.calendario.model.Calendario;
import com.microservicio.calendario.repository.CalendarioRepository;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository repository;

    public List<Calendario> listarTodos() { return repository.findAll(); }
    public Calendario guardar(Calendario c) { return repository.save(c); }
    public void eliminar(Long id) { repository.deleteById(id);}
}
