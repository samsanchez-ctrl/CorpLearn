package com.corplearn.calificacion.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.calificacion.model.Calificacion;
import com.corplearn.calificacion.service.CalificationService;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class CalificacionController extends BaseController {
    
    @Autowired
    private CalificationService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearCalificacion(@RequestBody Calificacion calificacion){
        try {
            Calificacion nueva = service.guardar(calificacion);
            return createResponse("Calificación registrada con éxito", nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Error al registrar calificación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodas() {
        try {
            List<Calificacion> lista = service.obtenerTodos();
            return createResponse("Calificaciones obtenidas exitosamente", lista, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al listar calificaciones: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Calificacion> lista = service.obtenerPorUsuario(usuarioId);
            return createResponse("Calificaciones del alumno obtenidas", lista, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al obtener notas del usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarCalificacion(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return createResponse("Calificación removida del sistema", null, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al eliminar la calificación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
