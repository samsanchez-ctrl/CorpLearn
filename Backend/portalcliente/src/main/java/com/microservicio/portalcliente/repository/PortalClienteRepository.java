package com.microservicio.portalcliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.portalcliente.model.PortalCliente;

public interface PortalClienteRepository extends JpaRepository <PortalCliente, Long> {
    
}
