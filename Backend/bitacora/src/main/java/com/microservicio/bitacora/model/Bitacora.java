package com.microservicio.bitacora.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BITACORA")
@Data
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long horarioId; // Relacion con Calendario
    private String contenidoTratado;
    private String observaciones;
    private LocalDateTime fechaRegistro;

    // Creo metodo para Bitacora Service por error de lombook
    public Long getHorarioId() {
        return horarioId;
    }
}
