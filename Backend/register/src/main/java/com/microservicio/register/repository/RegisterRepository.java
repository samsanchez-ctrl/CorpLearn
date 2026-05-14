package com.microservicio.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.register.model.UsuarioRegister;

public interface RegisterRepository extends JpaRepository<UsuarioRegister, Long> {
    
}
