package com.micriservicio.usuario.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nombre;
    private String correo;
    private Long id_rol; // El ID que se usara para la conexión
}
