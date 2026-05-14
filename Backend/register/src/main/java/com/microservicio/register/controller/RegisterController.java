package com.microservicio.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.register.dto.RegisterRequest;
import com.microservicio.register.service.RegisterService;

@RestController
@RequestMapping("/authRegister")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {
    @Autowired
    private RegisterService service;

    @PostMapping("")
    public Object registrar(@RequestBody RegisterRequest request) {
        return service.crear(request);
    }
}
