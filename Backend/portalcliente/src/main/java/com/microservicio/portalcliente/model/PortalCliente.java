package com.microservicio.portalcliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PORTAL_CLIENTE")
@Data
public class PortalCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empresa_id")
    private Long empresaId; // ID que referencia a la empresa cliente

    private String nombreEmpresa;

    private String contactoRrhh;

    private Long programaId; // FK logica portal cliente a programa

    private String tokenAcceso;

public Long getProgramaId() {
    return programaId;
}
}


