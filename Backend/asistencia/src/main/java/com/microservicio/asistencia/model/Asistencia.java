package com.microservicio.asistencia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ASISTENCIA")
@Data
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long alumnoId; // FK Logica a Usuario
    private Long horarioId; // FK Logica a Calendario
    private LocalDate fecha;
    private Boolean presente;
}
