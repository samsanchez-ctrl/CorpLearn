package com.corplearn.curso.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CURSO")
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String duracion; // Ej: "2 semanas"

    @Column(name = "image_url")
    private String imagenUrl; // Para que Angular muestre las fotos

}
