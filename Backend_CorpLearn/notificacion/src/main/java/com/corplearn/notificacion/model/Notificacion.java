package com.corplearn.notificacion.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "NOTIFICACION")
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private boolean leido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;
}
