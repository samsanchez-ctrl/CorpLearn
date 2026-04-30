package com.microservicio.rol.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROL")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;
}