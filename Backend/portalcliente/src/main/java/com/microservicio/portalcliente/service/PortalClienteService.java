package com.microservicio.portalcliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.microservicio.portalcliente.dto.ProgramaDto;
import com.microservicio.portalcliente.model.PortalCliente;
import com.microservicio.portalcliente.repository.PortalClienteRepository;

@Service
public class PortalClienteService {
    @Autowired
    private PortalClienteRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public List<PortalCliente> listarClientes() {
        return repository.findAll();
    }

    public PortalCliente registrarCliente(PortalCliente cliente) {
        try {
            // Validar que el Programa exista
            // Usamos getProgramaId() siguiendo tu estilo de CamelCase
            ProgramaDto programa = restTemplate.getForObject(
                "http://localhost:8088/programa/" + cliente.getProgramaId(), ProgramaDto.class);

            if (programa != null) {
                return repository.save(cliente);
            } else {
                throw new RuntimeException("Error: El programa asociado al cliente no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de comunicación: El microservicio de Programa no responde.");
        }
    }
}
