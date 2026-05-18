package com.corplearn.notificacion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corplearn.notificacion.model.Notificacion;
import com.corplearn.notificacion.repository.NotificacionRepository;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository repository;

    
    public Notificacion crearNotificacion(Notificacion notificacion) {
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setLeido(false); // Por defecto se crea sin leer
        return repository.save(notificacion);
    }

    public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioIdOrderByFechaEnvioDesc(usuarioId);
    }

    
    public List<Notificacion> obtenerNoLeidas(Long usuarioId) {
        return repository.findByUsuarioIdAndLeido(usuarioId, false);
    }

    
    public Notificacion marcarComoLeida(Long id) {
        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con el ID: " + id));
        
        notificacion.setLeido(true);
        return repository.save(notificacion);
    }

    
    public void borrarNotificacion(Long id) {
        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con el ID: " + id));
        repository.delete(notificacion);
    }
}
