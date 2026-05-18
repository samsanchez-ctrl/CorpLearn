package com.corplearn.register.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.register.model.Usuario;

@Repository
public interface RegisterRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
