package com.corplearn.inscripcion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.inscripcion.model.Inscripcion;
import com.corplearn.inscripcion.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin(origins = "http://localhost:4200")
public class InscripcionController {
    
    @Autowired
    private InscripcionService service;

    @PostMapping
    public ResponseEntity<?> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        try {
            return ResponseEntity.ok(service.inscribir(inscripcion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    
    @GetMapping
    public List<Inscripcion> listarTodas() {
        return service.obtenerTodas();
    }

    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Inscripcion>> listarPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok(service.obtenerPorUsuario(usuarioId));
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInscripcion(@PathVariable("id") Long id) {
        try {
            service.cancelarInscripcion(id);
            return ResponseEntity.ok("{\"mensaje\": \"Inscripción cancelada correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}

