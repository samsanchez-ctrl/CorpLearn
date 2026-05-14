package com.microservicio.modulo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.modulo.model.Modulo;
import com.microservicio.modulo.service.ModuloService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = "http://localhost:4200")
public class ModuloController {

    @Autowired
    private ModuloService service;

    @GetMapping
    public List<Modulo> listar(){
        return service.listar();
    }
    
    @PostMapping
    public Modulo guardar(@RequestBody Modulo modulo){
        return service.guardar(modulo);
    }

    @GetMapping("/{id}")
    public Modulo buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }



}
