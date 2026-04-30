package com.microservicio.portalcliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.portalcliente.model.PortalCliente;
import com.microservicio.portalcliente.service.PortalClienteService;



@RestController
@RequestMapping("/portalcliente")
public class PortalClienteController {
    @Autowired
    private PortalClienteService service;

    @GetMapping("")
    public List<PortalCliente> listar() {
        return service.listarClientes();
    }

    @PostMapping("")
    public PortalCliente crear (@RequestBody PortalCliente cliente) {
        return service.registrarCliente(cliente);
    }
}
