package com.corplearn.login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.login.dto.LoginRequest;
import com.corplearn.login.dto.LoginResponse;
import com.corplearn.login.service.LoginService;

@RestController
@RequestMapping("/authLogin")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController extends BaseController {
    
    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = service.autenticar(request);
            return createResponse("Autenticación exitosa", response, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Credenciales incorrectas o error en inicio de sesión: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
