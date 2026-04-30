package com.microservicio.portalcliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.portalcliente.model.PortalCliente;
import com.microservicio.portalcliente.repository.PortalClienteRepository;

@Service
public class PortalClienteService {
    @Autowired
    private PortalClienteRepository repository;

    public List<PortalCliente> listarClientes() {
        return repository.findAll();
    }

    public PortalCliente registrarCliente(PortalCliente cliente) {
        return repository.save(cliente);
    }
}
