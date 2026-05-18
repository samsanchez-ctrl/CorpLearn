package com.corplearn.login.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "rol_id")
    private Long rolId;
}
