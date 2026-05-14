package com.microservicio.mensajeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.mensajeria.model.Mensaje;
import com.microservicio.mensajeria.service.MensajeService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/mensajes")
@CrossOrigin(origins = "http://localhost:4200")
public class MensajeController {
    
    @Autowired
    private MensajeService service;

    @GetMapping
    public List<Mensaje> listar(){
        return service.listar();
    }

    @PostMapping
    public Mensaje enviar(@RequestBody Mensaje mensaje){
        return service.enviar(mensaje);
    }

}
