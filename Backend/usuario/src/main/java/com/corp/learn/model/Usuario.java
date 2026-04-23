package com.corp.usuario.model;

//import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nombre;

    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(
    //    name = "USUARIO_ROL",
    //    joinColumns = @JoinColumn(name = "USUARIO_ID"),
    //    inverseJoinColumns = @JoinColumn(name = "ROL_ID")
    //)
    //private Set<Rol> ROL;

    private String rolNombre;
}
