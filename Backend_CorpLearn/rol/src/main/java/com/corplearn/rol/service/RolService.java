package com.corplearn.rol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corplearn.rol.dto.RolDto;
import com.corplearn.rol.model.Rol;
import com.corplearn.rol.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository repository;

    public List<Rol> obtenerTodos() {
        return repository.findAll();
    }

    public Rol obtenerPorId(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    public Rol crear(RolDto dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.getNombre().toUpperCase());
        rol.setDescripcion(dto.getDescripcion());
        return repository.save(rol);
    }

    public Rol actualizar(Long id, RolDto dto) {
        Rol rol = obtenerPorId(id);
        rol.setNombre(dto.getNombre().toUpperCase());
        rol.setDescripcion(dto.getDescripcion());
        return repository.save(rol);
    }

    public void eliminar(Long id) {
        Rol rol = obtenerPorId(id);
        repository.delete(rol);
    }
}
