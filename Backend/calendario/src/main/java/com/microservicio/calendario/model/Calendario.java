package com.microservicio.calendario.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CALENDARIO")
@Data
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long moduloId; //FK logica a microservicio Modulo
    private Long relatorId; //FK logica a microservicio Usuario (Rol Relator)
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String sala;  
}
