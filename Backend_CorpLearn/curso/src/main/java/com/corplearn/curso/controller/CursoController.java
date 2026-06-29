package com.corplearn.curso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController extends BaseController {
    
    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearCurso(@RequestBody Curso curso) {
        try {
            Curso nuevoCurso = service.guardar(curso);
            return createResponse("Curso creado exitosamente", nuevoCurso, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Error al crear el curso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarCursos() {
        try {
            List<Curso> cursos = service.obtenerTodos();
            return createResponse("Lista de cursos obtenida con éxito", cursos, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al listar los cursos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarCurso(@PathVariable("id") Long id) {
        try {
            Curso curso = service.obtenerPorId(id);
            return createResponse("Curso encontrado exitosamente", curso, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("No se pudo encontrar el curso: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificarCurso(@PathVariable("id") Long id, @RequestBody Curso cursoDetalles) {
        try {
            Curso cursoActualizado = service.actualizar(id, cursoDetalles);
            return createResponse("Curso modificado exitosamente", cursoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al modificar el curso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarCurso(@PathVariable("id") Long id) {
        try {
            service.eliminar(id);
            return createResponse("Curso eliminado exitosamente del sistema.", null, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al eliminar el curso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}