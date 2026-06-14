package com.corplearn.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    
    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = service.autenticar(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
