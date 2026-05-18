package com.corplearn.rol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.rol.dto.RolDto;
import com.corplearn.rol.model.Rol;
import com.corplearn.rol.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {
    
    @Autowired
    private RolService service;

    @GetMapping
    public ResponseEntity<List<Rol>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Rol> guardar(@RequestBody RolDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> modificar(@PathVariable Long id, @RequestBody RolDto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Rol eliminado correctamente con ID: " + id);
    }
}
