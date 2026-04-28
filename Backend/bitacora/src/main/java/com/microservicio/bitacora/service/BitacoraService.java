package com.microservicio.bitacora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.bitacora.model.Bitacora;
import com.microservicio.bitacora.repository.BitacoraRepository;

@Service
public class BitacoraService {
    
    @Autowired
    private BitacoraRepository repository;

    public List<Bitacora> listarTodas(){
        return repository.findAll();
    }

    public Bitacora guardar(Bitacora bitacora) {
        return repository.save(bitacora);
    }

    public Bitacora obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
