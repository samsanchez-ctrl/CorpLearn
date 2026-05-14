package com.microservicio.calendario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.calendario.model.Calendario;
import com.microservicio.calendario.service.CalendarioService;

@RestController
@RequestMapping("/calendario")
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarioController {
    @Autowired
    private CalendarioService service;

    @GetMapping
    public List<Calendario> listar() { return service.listarTodos(); }

    @PostMapping
    public Calendario crear(@RequestBody Calendario c) { return service.guardar(c); }

}
