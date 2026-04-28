package com.microservicio.nota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.nota.model.Nota;
import com.microservicio.nota.service.NotaService;

@RestController
@RequestMapping("/notas")
public class NotaController {
    
    @Autowired
    private NotaService service;

    @PostMapping("")
    public Nota crear(@RequestBody Nota n) { return service.subirNota(n); }
}
