package com.corplearn.notificacion.controller;

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

import com.corplearn.notificacion.model.Notificacion;
import com.corplearn.notificacion.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificacionController extends BaseController {
    
    @Autowired
    private NotificacionService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> enviar(@RequestBody Notificacion notificacion) {
        try {
            Notificacion nuevaNotificacion = service.crearNotificacion(notificacion);
            return createResponse("Notificación enviada con éxito", nuevaNotificacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("No se pudo enviar la notificación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        try {
            List<Notificacion> notificaciones = service.obtenerPorUsuario(usuarioId);
            return createResponse("Notificaciones del usuario obtenidas", notificaciones, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al obtener las notificaciones: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<Map<String, Object>> listarNoLeidas(@PathVariable("usuarioId") Long usuarioId) {
        try {
            List<Notificacion> noLeidas = service.obtenerNoLeidas(usuarioId);
            return createResponse("Notificaciones no leídas obtenidas", noLeidas, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al obtener las notificaciones no leídas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<Map<String, Object>> leer(@PathVariable("id") Long id) {
        try {
            Notificacion notificacionLeida = service.marcarComoLeida(id);
            return createResponse("Notificación marcada como leída", notificacionLeida, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al actualizar estado de notificación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> borrar(@PathVariable("id") Long id) {
        try {
            service.borrarNotificacion(id);
            return createResponse("Notificación eliminada correctamente.", null, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al eliminar la notificación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
