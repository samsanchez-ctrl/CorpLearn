package com.microservicio.modulo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.modulo.model.Modulo;
import com.microservicio.modulo.repository.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository repository;

    public List<Modulo> listar(){
        return repository.findAll();
    }

    public Modulo guardar(Modulo modulo){
        return repository.save(modulo);
    }

    public Modulo buscar(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }


}
