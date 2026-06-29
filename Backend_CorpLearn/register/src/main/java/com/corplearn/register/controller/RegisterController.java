package com.corplearn.register.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class RegisterController extends BaseController {
    
    @Autowired
    private RegisterService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody RegisterRequest request) {
        try {
            Object usuarioRegistrado = service.registrarUsuario(request);
            return createResponse("Usuario registrado exitosamente en el sistema", usuarioRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Error en el registro de usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
