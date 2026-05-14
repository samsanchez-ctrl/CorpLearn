package com.microservicio.programa.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.programa.model.Programa;
import com.microservicio.programa.service.ProgramaService;

@RestController
@RequestMapping("/programas")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgramaController {

    private final ProgramaService programaService;

    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    @PostMapping("")
    public ResponseEntity<Programa> crear(@RequestBody Programa programa) {
        return ResponseEntity.ok(programaService.guardarPrograma(programa));
    }

    @GetMapping("")
    public ResponseEntity<List<Programa>> listar() {
        return ResponseEntity.ok(programaService.listarTodo());
    }
}