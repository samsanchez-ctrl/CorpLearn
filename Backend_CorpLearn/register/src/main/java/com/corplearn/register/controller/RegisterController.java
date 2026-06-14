package com.corplearn.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.register.dto.RegisterRequest;
import com.corplearn.register.service.RegisterService;

@RestController
@RequestMapping("/authRegister")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {
    
    @Autowired
    private RegisterService service;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.registrarUsuario(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
