package com.microservicio.rol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.microservicio.rol.model.Rol;
import com.microservicio.rol.service.RolService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService service;

    @GetMapping
    public List<Rol> listar() {
        return service.listar();
    }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) {
        return service.guardar(rol);
    }

    @GetMapping("/{id}")
    public Rol buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}