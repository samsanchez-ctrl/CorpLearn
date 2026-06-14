package com.corplearn.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.curso.model.Curso;
import com.corplearn.curso.service.CursoService;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {
    
    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(service.guardar(curso));
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCurso(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCurso(@PathVariable("id") Long id, @RequestBody Curso cursoDetalles) {
        try {
            Curso cursoActualizado = service.actualizar(id, cursoDetalles);
            return ResponseEntity.ok(cursoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable("id") Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("{\"mensaje\": \"Curso eliminado exitosamente del sistema.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
