package com.corplearn.calificacion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "CALIFICACION")
@Data
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "curso_id", nullable = false)
    private Long cursoId;

    @Column(nullable = false)
    private Double nota;

    private String comentario;

    @Column(name = "fecha_evaluacion")
    private LocalDateTime fechaEvaluacion = LocalDateTime.now();
}
