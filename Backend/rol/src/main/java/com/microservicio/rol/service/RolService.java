package com.microservicio.rol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservicio.rol.model.Rol;
import com.microservicio.rol.repository.RolRepository;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public List<Rol> listar() {
        return repository.findAll();
    }

    public Rol guardar(Rol rol) {
        return repository.save(rol);
    }

    public Rol buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}