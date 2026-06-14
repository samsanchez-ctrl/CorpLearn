package com.corplearn.notificacion.controller;

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

import com.corplearn.notificacion.model.Notificacion;
import com.corplearn.notificacion.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificacionController {
    @Autowired
    private NotificacionService service;

    
    @PostMapping
    public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(service.crearNotificacion(notificacion));
    }

    
    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return service.obtenerPorUsuario(usuarioId);
    }

    
    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public List<Notificacion> listarNoLeidas(@PathVariable("usuarioId") Long usuarioId) {
        return service.obtenerNoLeidas(usuarioId);
    }

    
    @PutMapping("/{id}/leer")
    public ResponseEntity<?> leer(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.marcarComoLeida(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        try {
            service.borrarNotificacion(id);
            return ResponseEntity.ok("{\"mensaje\": \"Notificación eliminada correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
