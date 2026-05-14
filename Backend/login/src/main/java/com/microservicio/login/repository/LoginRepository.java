package com.microservicio.login.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.login.model.UsuarioLogin;

public interface LoginRepository extends JpaRepository<UsuarioLogin, Long> {
    Optional<UsuarioLogin> findByEmail(String email);
}
