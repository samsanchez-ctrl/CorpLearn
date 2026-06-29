package com.corplearn.inscripcion.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.inscripcion.model.Inscripcion;
import com.corplearn.inscripcion.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin(origins = "http://localhost:4200")
public class InscripcionController extends BaseController {
    
    @Autowired
    private InscripcionService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion nuevaInscripcion = service.inscribir(inscripcion);
            return createResponse("Inscripción realizada correctamente", nuevaInscripcion, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Error al procesar la inscripción: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodas() {
        try {
            List<Inscripcion> inscripciones = service.obtenerTodas();
            return createResponse("Lista de todas las inscripciones obtenida", inscripciones, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al obtener las inscripciones: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        try {
            List<Inscripcion> inscripcionesUsuario = service.obtenerPorUsuario(usuarioId);
            return createResponse("Inscripciones del usuario obtenidas", inscripcionesUsuario, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al obtener las inscripciones del usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarInscripcion(@PathVariable("id") Long id) {
        try {
            service.cancelarInscripcion(id);
            return createResponse("Inscripción cancelada correctamente.", null, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al cancelar la inscripción: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

