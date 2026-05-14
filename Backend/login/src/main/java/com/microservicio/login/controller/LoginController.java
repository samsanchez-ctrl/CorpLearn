package com.microservicio.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.login.dto.LoginRequest;
import com.microservicio.login.service.LoginService;

@RestController
@RequestMapping("/authLogin")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping("")
    public Object login(@RequestBody LoginRequest request) {
        return service.validar(request);
    }
}
