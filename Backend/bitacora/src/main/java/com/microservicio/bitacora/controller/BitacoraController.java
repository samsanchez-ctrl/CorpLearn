package com.microservicio.bitacora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.bitacora.model.Bitacora;
import com.microservicio.bitacora.service.BitacoraService;

@RestController
@RequestMapping("/bitacora")
public class BitacoraController {
    
    @Autowired
    private BitacoraService service;

    @GetMapping("")
    public List<Bitacora> listar() {
        return service.listarTodas();
    }

    @PostMapping("")
    public Bitacora crear(@RequestBody Bitacora bitacora) {
        return service.guardar(bitacora);
    }
}
