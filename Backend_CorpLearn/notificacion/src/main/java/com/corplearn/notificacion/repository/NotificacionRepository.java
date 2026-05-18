package com.corplearn.notificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.notificacion.model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository <Notificacion, Long> {
    List<Notificacion> findByUsuarioIdOrderByFechaEnvioDesc(Long usuarioId);
    List<Notificacion> findByUsuarioIdAndLeido(Long usuarioId, boolean leido);
}
