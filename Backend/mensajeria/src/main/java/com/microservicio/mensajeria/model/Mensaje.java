package com.microservicio.mensajeria.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MENSAJERIA")
@Data
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emisor;
    private String receptor;
    private String contenido;

    private LocalDateTime fecha;

    public String getEmisorId() {
        return emisor;
    }

    public String getReceptorId() {
        return receptor;
    }
}
