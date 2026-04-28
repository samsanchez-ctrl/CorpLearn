package com.microservicio.asistencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.asistencia.model.Asistencia;
import com.microservicio.asistencia.service.AsistenciaService;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {
    
    @Autowired
    private AsistenciaService service;
    
    @PostMapping("")
    public Asistencia marcar(@RequestBody Asistencia a) { return service.registrar(a); }
}
